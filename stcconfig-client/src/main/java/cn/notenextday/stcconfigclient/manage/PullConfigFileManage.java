package cn.notenextday.stcconfigclient.manage;

import cn.notenextday.stcconfigclient.bean.DemoBean;
import cn.notenextday.stcconfigclient.constant.NodePathContant;
import cn.notenextday.stcconfigclient.container.BeanContainer;
import cn.notenextday.stcconfigclient.container.BeanNode;
import cn.notenextday.stcconfigclient.container.ConfigContainer;
import cn.notenextday.stcconfigclient.container.FieldNode;
import cn.notenextday.stcconfigclient.dto.NodeDTO;
import cn.notenextday.stcconfigclient.enums.ConfigFileTypeEnum;
import cn.notenextday.stcconfigclient.util.FileReadToTypeUtil;
import cn.notenextday.stcconfigclient.util.TypeUtil;
import cn.notenextday.stcconfigclient.util.ZookeeperClientUtil;
import cn.notenextday.stcconfigclient.watcher.NodeActionWatcher;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
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
import java.util.Objects;

/**
 * 配置文件管理核心
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/8/3 14:53
 */
@Order(value = 1)
@Service
public class PullConfigFileManage implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(PullConfigFileManage.class);
    private String STCCONFIG_FILE_PATH_DEFAULT = this.getClass().getResource("/").getPath() + "stcconfig" + "/";
    private String stcconfigFilePath = STCCONFIG_FILE_PATH_DEFAULT;
    @Value("${server.stcconfig.envId}")
    private Integer envId;
    @Value("${server.stcconfig.projectId}")
    private Integer projectId;
    @Value("${server.stcconfig.zookeeperUrl}")
    private String zookeeperUrl;
    @Value("${server.stcconfig.directory}")
    private String directory;
    @Resource
    private PullConfigFileManage pullConfigFileManage;
    @Resource
    private DemoBean demoBean;

    /**
     * 配置文件初始化
     */
    public void init() {
        // 增加监控当前项目节点(当变化后当前方法被重新执行,节点重新被监控, 配置文件重新下载, 更新容器中的值, 不支持服务器删除配置文件, 需要重启后生效)
        if (!ZookeeperClientUtil.existsNode(NodePathContant.ENV_PATH_ALL + NodePathContant.PATH_SUB + envId + NodePathContant.PROJECT_PATH + NodePathContant.PATH_SUB + projectId, new NodeActionWatcher(pullConfigFileManage))) {
            logger.warn("[stcconfig][client]注册中心未找到配置节点,请检查");
            return;
        }
        // 下载配置文件
        pullConfigFile();
        // 根据容器(bean, 配置), 更新bean中的变量值
        Map<String, BeanNode> beanMap = BeanContainer.container().getDataMap();
        Map<String, Map<String, Object>> configMap = ConfigContainer.container().getDataMap();
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
                Map<String, Object> config = configMap.get(fieldNode.getAnnoFileName());
                String configValue = TypeUtil.ObjToString(config.get(fieldNode.getAnnokeyName()));
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
                logger.warn("[stcconfig][client]下载配置文件:{}{}", stcconfigFilePath, nodeDTO.getFileName());
                // 请求HTTP请求, 获取配置文件,拷贝到本地
                URL remoteUrl = new URL(nodeDTO.getPath());
                File localTmpFile = new File(stcconfigFilePath + nodeDTO.getFileName());
                FileUtils.copyURLToFile(remoteUrl, localTmpFile);
                int pointIndex = nodeDTO.getFileName().indexOf(".");
                ConfigFileTypeEnum configFileTypeEnum = ConfigFileTypeEnum.getEnumByName(nodeDTO.getFileName().substring(pointIndex));
                if (Objects.isNull(configFileTypeEnum)) {
                    logger.error("[stcconfig][client]文件解析失败,不支持该类型:{}{}", stcconfigFilePath, nodeDTO.getFileName());
                    continue;
                }
                // 读取配置文件内容到配置容器
                Map<String, Object> dataMap = new HashMap<>();
                if (ConfigFileTypeEnum.YAML.equals(configFileTypeEnum)) {
                    dataMap = FileReadToTypeUtil.yaml2Map(stcconfigFilePath + nodeDTO.getFileName());
                }
                if (ConfigFileTypeEnum.PROPERTIES.equals(configFileTypeEnum)) {
                    dataMap = FileReadToTypeUtil.properties2Map(stcconfigFilePath + nodeDTO.getFileName());
                }
                if (ConfigFileTypeEnum.TXT.equals(configFileTypeEnum)) {
                    dataMap = FileReadToTypeUtil.properties2Map(stcconfigFilePath + nodeDTO.getFileName());
                }
                ConfigContainer.container().getDataMap().put(nodeDTO.getFileName(), dataMap);
            }
        } catch (IOException e) {
            logger.error("[stcconfig][client] 请求服务器拉取配置文件失败");
        }
    }

    /**
     * Spring 启动初始化
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        ZookeeperClientUtil.init(zookeeperUrl);
        if (!Strings.isEmpty(directory)) {
            stcconfigFilePath = this.getClass().getResource("/").getPath() + directory + "/";
        }
        this.init();
    }


}
