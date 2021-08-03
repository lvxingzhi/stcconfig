package cn.notenextday.stcconfigclient.manage;

import cn.notenextday.stcconfigclient.constant.NodePathContant;
import cn.notenextday.stcconfigclient.dto.NodeDTO;
import cn.notenextday.stcconfigclient.util.ZookeeperClientUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    private String stcconfigFilePath = "c:/work/";

    /**
     * 从ZK获取配置路径列表
     */
    public void pullConfigFile(Integer envId, Integer projectId) throws IOException {
        if (!ZookeeperClientUtil.existsNode(NodePathContant.ENV_PATH_ALL + NodePathContant.PATH_SUB + envId + NodePathContant.PROJECT_PATH + NodePathContant.PATH_SUB + projectId)) {
            return;
        }
        List<NodeDTO> nodeDTOList = ZookeeperClientUtil.getChildrenNodes(NodePathContant.ENV_PATH_ALL + NodePathContant.PATH_SUB + envId + NodePathContant.PROJECT_PATH + NodePathContant.PATH_SUB + projectId);
        if (CollectionUtils.isEmpty(nodeDTOList)) {
            return;
        }
        for (NodeDTO nodeDTO : nodeDTOList) {
            // 请求HTTP请求, 获取配置文件
            URL remoteUrl = new URL(nodeDTO.getPath());
            File localTmpFile = new File(stcconfigFilePath + nodeDTO.getFileName());
            FileUtils.copyURLToFile(remoteUrl, localTmpFile);
        }
    }

}
