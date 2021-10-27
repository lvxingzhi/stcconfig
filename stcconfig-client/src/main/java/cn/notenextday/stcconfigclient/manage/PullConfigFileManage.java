package cn.notenextday.stcconfigclient.manage;

import cn.notenextday.stcconfigclient.annotations.StcconfigValue;
import cn.notenextday.stcconfigclient.bean.DemoBean;
import cn.notenextday.stcconfigclient.constant.NodePathContant;
import cn.notenextday.stcconfigclient.container.BeanContainer;
import cn.notenextday.stcconfigclient.container.BeanNode;
import cn.notenextday.stcconfigclient.container.ConfigContainer;
import cn.notenextday.stcconfigclient.container.FieldNode;
import cn.notenextday.stcconfigclient.dto.NodeDTO;
import cn.notenextday.stcconfigclient.util.ZookeeperClientUtil;
import cn.notenextday.stcconfigclient.watcher.NodeActionWatcher;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置文件管理核心
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/8/3 14:53
 */
@Service
public class PullConfigFileManage implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(PullConfigFileManage.class);
    private String stcconfigFilePath = "./src/main/resources/stcconfig/";
    @Value("${server.stcconfig.envId}")
    private Integer envId;
    @Value("${server.stcconfig.projectId}")
    private Integer projectId;
    @StcconfigValue(fileName = "app.properties", key = "123")
    private String testValue;
    @Resource
    private PullConfigFileManage pullConfigFileManage;
    @Resource
    private DemoBean demoBean;

    /**
     * 配置文件初始化
     */
    public void init() {
        // 增加监控
        if (!ZookeeperClientUtil.existsNode(NodePathContant.ENV_PATH_ALL + NodePathContant.PATH_SUB + envId + NodePathContant.PROJECT_PATH + NodePathContant.PATH_SUB + projectId, new NodeActionWatcher(pullConfigFileManage))) {
            logger.warn("[stcconfig][client]注册中心未找到配置节点,请检查");
            return;
        }
        // 下载配置文件
        pullConfigFile();
        // 根据容器(bean, 配置), 更新bean中的变量值
        Map<String, BeanNode> beanMap = BeanContainer.container().getDataMap();
        Map<String, Map<String, String>> configMap = ConfigContainer.container().getDataMap();
        if (beanMap.size() < 0) {
            return;
        }
        beanMap.forEach((key, value) -> {
            Object object = value.getObject();
            List<FieldNode> fieldNodeList = value.getFieldList();
            if (CollectionUtils.isEmpty(fieldNodeList)) {
                return;
            }
            for (FieldNode fieldNode : fieldNodeList) {
                Map<String, String> config = configMap.get(fieldNode.getAnnoFileName());
                String configValue = config.get(fieldNode.getAnnokeyName());
                BeanMap springBeanMap = BeanMap.create(object);
                springBeanMap.put(fieldNode.getFieldName(), configValue);
                logger.info("[stcconfig][client]赋值,fieldName:{}, value:{}", fieldNode.getFieldName(), configValue);
            }
        });
        logger.info("[stcconfig][client]测试赋值是否成功: demoBean.name={}", demoBean.getName());
    }

    /**
     * 文件下载
     */
    public void pullConfigFile() {
        try {
            List<NodeDTO> nodeDTOList = ZookeeperClientUtil.getChildrenNodes(NodePathContant.ENV_PATH_ALL + NodePathContant.PATH_SUB + envId + NodePathContant.PROJECT_PATH + NodePathContant.PATH_SUB + projectId);
            if (CollectionUtils.isEmpty(nodeDTOList)) {
                logger.warn("[stcconfig][client]注册中心配置为空");
                return;
            }
            for (NodeDTO nodeDTO : nodeDTOList) {
                logger.warn("[stcconfig][client]下载配置文件:{}", nodeDTO.getFileName());
                // 请求HTTP请求, 获取配置文件,拷贝到本地
                URL remoteUrl = new URL(nodeDTO.getPath());
                File localTmpFile = new File(stcconfigFilePath + nodeDTO.getFileName());
                FileUtils.copyURLToFile(remoteUrl, localTmpFile);
                // 读取配置文件内容到配置容器
                try (
                        FileInputStream fileInputStream = new FileInputStream(localTmpFile);
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                ) {
                    Map<String, String> dataMap = new HashMap<>();
                    String configKeyAndValue = "";
                    while ((configKeyAndValue = bufferedReader.readLine()) != null) {
                        logger.info("[stcconfig][client] 读取配置: {}", configKeyAndValue);
                        // TODO 判断文件后缀名决定解析方式
                        if (Strings.isEmpty(configKeyAndValue)) {
                            continue;
                        }
                        int index = configKeyAndValue.indexOf("=");
                        if (index == -1) {
                            continue;
                        }
                        String key = configKeyAndValue.substring(0, index);
                        String value = configKeyAndValue.substring(index);
                        dataMap.put(key, value);
                    }
                    ConfigContainer.container().getDataMap().put(nodeDTO.getFileName(), dataMap);
                } catch (Exception ex) {
                    logger.error("[stcconfig][client] 读取配置文件失败");
                }
            }
        } catch (IOException e) {
            logger.error("[stcconfig][client] 请求服务器拉取配置文件失败");
        }
    }

    @Override
    public void run(String... args) throws Exception {
        this.init();
    }
}
