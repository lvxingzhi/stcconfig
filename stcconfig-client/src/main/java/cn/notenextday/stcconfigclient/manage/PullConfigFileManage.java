package cn.notenextday.stcconfigclient.manage;

import cn.notenextday.stcconfigclient.constant.NodePathContant;
import cn.notenextday.stcconfigclient.dto.NodeDTO;
import cn.notenextday.stcconfigclient.util.ZookeeperClientUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/8/3 14:53
 */
@Service
public class PullConfigFileManage {

    /**
     * 从ZK获取配置路径列表
     */
    public void pullConfigFile(Integer envId, Integer projectId) throws IOException {
        if (!ZookeeperClientUtil.existsNode(NodePathContant.ENV_PATH_ALL + NodePathContant.PATH_SUB + envId + NodePathContant.PROJECT_PATH + NodePathContant.PATH_SUB + projectId)) {
            return;
        }
        List<NodeDTO> nodeDTOList = ZookeeperClientUtil.getChildrenNodes(NodePathContant.ENV_PATH_ALL + NodePathContant.PATH_SUB + envId + NodePathContant.PROJECT_PATH + NodePathContant.PATH_SUB + projectId);
        // 请求HTTP请求, 获取配置文件
        URL remoteUrl = new URL("http://localhost:8100/client/getConfigFile?path=/stcconfig/env/1/project/1/1");
        File localTmpFile = new File("c:/work/ap.properties");
        FileUtils.copyURLToFile(remoteUrl, localTmpFile);
    }

}
