package cn.notenextday.stcconfigclient.manage;

import cn.notenextday.stcconfigclient.constant.NodePathContant;
import cn.notenextday.stcconfigclient.dto.NodeDTO;
import cn.notenextday.stcconfigclient.util.ZookeeperClientUtil;
import org.springframework.stereotype.Service;

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
    public void pullConfigFile(Integer envId, Integer projectId) {
        if(!ZookeeperClientUtil.existsNode(NodePathContant.ENV_PATH_ALL + NodePathContant.PATH_SUB + envId + NodePathContant.PROJECT_PATH + projectId)){
            return;
        }
        List<NodeDTO> nodeDTOList= ZookeeperClientUtil.getChildrenNodes(NodePathContant.ENV_PATH_ALL + NodePathContant.PATH_SUB + envId + NodePathContant.PROJECT_PATH + projectId);
    }

}
