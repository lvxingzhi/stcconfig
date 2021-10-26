package cn.notenextday.stcconfigclient.manage;

import cn.notenextday.stcconfigclient.annotations.StcconfigValue;
import cn.notenextday.stcconfigclient.constant.NodePathContant;
import cn.notenextday.stcconfigclient.container.ConfigContainer;
import cn.notenextday.stcconfigclient.dto.NodeDTO;
import cn.notenextday.stcconfigclient.util.ZookeeperClientUtil;
import cn.notenextday.stcconfigclient.watcher.NodeActionWatcher;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
public class PullConfigFileManage {
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

    /**
     * 配置文件初始化
     */
    public void init() {
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++:" + testValue);
        // 增加监控
        if (!ZookeeperClientUtil.existsNode(NodePathContant.ENV_PATH_ALL + NodePathContant.PATH_SUB + envId + NodePathContant.PROJECT_PATH + NodePathContant.PATH_SUB + projectId, new NodeActionWatcher(pullConfigFileManage))) {
            logger.warn("[stcconfig][client]注册中心未找到配置节点,请检查");
            return;
        }
        // 下载配置文件
        pullConfigFile();
        // TODO 根据bean容器, 配置容器, 更新bean中的变量值

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
}
