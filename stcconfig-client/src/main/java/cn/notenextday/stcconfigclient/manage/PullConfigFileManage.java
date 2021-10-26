package cn.notenextday.stcconfigclient.manage;

import cn.notenextday.stcconfigclient.annotations.StcconfigValue;
import cn.notenextday.stcconfigclient.constant.NodePathContant;
import cn.notenextday.stcconfigclient.dto.NodeDTO;
import cn.notenextday.stcconfigclient.util.ZookeeperClientUtil;
import cn.notenextday.stcconfigclient.watcher.NodeActionWatcher;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * 配置文件管理核心
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
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++:"+testValue);
        // 增加监控
        if (!ZookeeperClientUtil.existsNode(NodePathContant.ENV_PATH_ALL + NodePathContant.PATH_SUB + envId + NodePathContant.PROJECT_PATH + NodePathContant.PATH_SUB + projectId, new NodeActionWatcher(pullConfigFileManage))) {
            logger.warn("[stcconfig][client]注册中心未找到配置节点,请检查");
            return;
        }
        // 下载配置文件
        pullConfigFile();
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
                // 请求HTTP请求, 获取配置文件
                URL remoteUrl = new URL(nodeDTO.getPath());
                File localTmpFile = new File(stcconfigFilePath + nodeDTO.getFileName());
                FileUtils.copyURLToFile(remoteUrl, localTmpFile);
                // TODO 读取配置文件内容到配置容器
            }
        } catch (IOException e) {
            logger.error("[stcconfig][client] 请求服务器拉取配置文件失败");
        }
    }

}
