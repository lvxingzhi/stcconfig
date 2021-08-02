package cn.notenextday.stcconfig.manage;

import cn.notenextday.stcconfig.constant.NodePathContant;
import cn.notenextday.stcconfig.dto.ConfigInfoDO;
import cn.notenextday.stcconfig.dto.NodeDTO;
import cn.notenextday.stcconfig.dto.ProjectInfoDO;
import cn.notenextday.stcconfig.util.ZookeeperClientUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Zk 管理器
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/28 11:29
 */
@Repository
public class StcconfigRegisterZkManage extends StcconfigRegisterManage {
    private static final Logger logger = LoggerFactory.getLogger(ZookeeperClientUtil.class);
    private static final String DEFAULT_VERSION = "0";
    private static final String ROOT_DATA = "{\"path\":\"/stcconfig\",\"data\":\"stcconfig\"}";

    @Override
    public boolean checkAliveStatus() {
        try {
            ZookeeperClientUtil.getNode(NodePathContant.ROOT_PATH, null);
            return true;
        } catch (Exception e) {
            logger.error("[zookeeper] 检查存活状态异常{}", e);
        }
        return false;
    }

    @Override
    public NodeDTO getRootNode() {
        if (ZookeeperClientUtil.existsNode(NodePathContant.ROOT_PATH)) {
            return ZookeeperClientUtil.getNode(NodePathContant.ROOT_PATH, null);
        }
        return null;
    }

    @Override
    public boolean createRootNode() {
        if (Objects.isNull(getRootNode())) {
            ZookeeperClientUtil.createNode(NodePathContant.ROOT_PATH, ROOT_DATA.getBytes(StandardCharsets.UTF_8), CreateMode.PERSISTENT);
            return true;
        }
        return false;
    }

    @Override
    public boolean buildNodes() {
        if (!checkAliveStatus()) {
            return false;
        }
        if (Objects.isNull(getRootNode())) {
            if (createRootNode()) {
                return false;
            }
        }
        // 判断/env是否创建
        if (!ZookeeperClientUtil.existsNode(NodePathContant.ROOT_PATH + NodePathContant.ENV_PATH)) {
            ZookeeperClientUtil.createNode(NodePathContant.ROOT_PATH + NodePathContant.ENV_PATH, JSONObject.toJSONBytes(new NodeDTO("", "", DEFAULT_VERSION)), CreateMode.PERSISTENT);
        }
        // 获取ZK环境列表
        List<NodeDTO> zkEnvNodeList = ZookeeperClientUtil.getChildrenNodes(NodePathContant.ROOT_PATH + NodePathContant.ENV_PATH);
        // 以ZK为基准对比DB, 没有则删除ZK, (没有配置版本对比)
        for (NodeDTO zkEnvNodeDTO : zkEnvNodeList) {
            if (!getEnvProjectMap().keySet().contains(Integer.parseInt(zkEnvNodeDTO.getData()))) {
                // 删除ZK环境
                ZookeeperClientUtil.deleteNode(zkEnvNodeDTO.getPath());
            }

            // 判断/project是否创建
            if (!ZookeeperClientUtil.existsNode(zkEnvNodeDTO.getPath() + NodePathContant.PROJECT_PATH)) {
                ZookeeperClientUtil.createNode(zkEnvNodeDTO.getPath() + NodePathContant.PROJECT_PATH, JSONObject.toJSONBytes(new NodeDTO(zkEnvNodeDTO.getPath() + NodePathContant.PROJECT_PATH, "env", DEFAULT_VERSION)), CreateMode.PERSISTENT);
            }
            // 获取ZK项目列表
            List<NodeDTO> zkProjectNodeList = ZookeeperClientUtil.getChildrenNodes(zkEnvNodeDTO.getPath()+ NodePathContant.PROJECT_PATH);
            for (NodeDTO zkProNodeDTO : zkProjectNodeList) {
                if (CollectionUtils.isEmpty(getEnvProjectMap().get(Integer.parseInt(zkEnvNodeDTO.getData())).stream().filter(s -> s.getId().equals(Integer.parseInt(zkProNodeDTO.getData()))).collect(Collectors.toList()))) {
                    // 删除ZK项目
                    ZookeeperClientUtil.deleteNode(zkEnvNodeDTO.getPath() + NodePathContant.PROJECT_PATH + NodePathContant.PATH_SUB + zkProNodeDTO.getPath());
                }

                // 获取ZK配置列表
                List<NodeDTO> zkConfigNodeList = ZookeeperClientUtil.getChildrenNodes(zkProNodeDTO.getPath());
                for (NodeDTO zkConfigNodeDTO : zkConfigNodeList) {
                    if (CollectionUtils.isEmpty(getProConfigMap().get(Integer.parseInt(zkProNodeDTO.getData())).stream().filter(s -> s.getId().equals(Integer.parseInt(zkConfigNodeDTO.getData()))).collect(Collectors.toList()))) {
                        // 删除ZK配置
                        ZookeeperClientUtil.deleteNode(zkProNodeDTO.getPath() + NodePathContant.PATH_SUB + zkConfigNodeDTO.getPath());
                    }
                }
            }
        }
        // 以DB为基准对比ZK, 没有则新增ZK, (配置版本对比更新)
        for (Integer dbEnvId : getEnvProjectMap().keySet()) {
            if (CollectionUtils.isEmpty(zkEnvNodeList.stream().filter(s -> s.getPath().endsWith(NodePathContant.PATH_SUB + dbEnvId)).collect(Collectors.toList()))) {
                // 创建ZK环境
                String zkEnvNodePath = NodePathContant.ROOT_PATH + NodePathContant.ENV_PATH + NodePathContant.PATH_SUB + dbEnvId;
                ZookeeperClientUtil.createNode(zkEnvNodePath, JSONObject.toJSONBytes(new NodeDTO(zkEnvNodePath, dbEnvId, DEFAULT_VERSION)), CreateMode.PERSISTENT);
            }

            // 判断/project是否创建
            if (!ZookeeperClientUtil.existsNode(NodePathContant.ROOT_PATH + NodePathContant.ENV_PATH + NodePathContant.PATH_SUB + dbEnvId + NodePathContant.PROJECT_PATH)) {
                ZookeeperClientUtil.createNode(NodePathContant.ROOT_PATH + NodePathContant.ENV_PATH + NodePathContant.PATH_SUB + dbEnvId + NodePathContant.PROJECT_PATH, JSONObject.toJSONBytes(new NodeDTO(NodePathContant.ROOT_PATH + NodePathContant.ENV_PATH + NodePathContant.PATH_SUB + dbEnvId + NodePathContant.PROJECT_PATH, "env", DEFAULT_VERSION)), CreateMode.PERSISTENT);
            }
            // 获取ZK项目列表
            List<NodeDTO> zkProjectNodeList = ZookeeperClientUtil.getChildrenNodes(NodePathContant.ROOT_PATH + NodePathContant.ENV_PATH + NodePathContant.PATH_SUB + dbEnvId + NodePathContant.PROJECT_PATH);
            for (ProjectInfoDO dbProjectInfoDO : getEnvProjectMap().get(dbEnvId)) {
                if (CollectionUtils.isEmpty(zkProjectNodeList.stream().filter(s -> s.getPath().endsWith(NodePathContant.PATH_SUB + dbProjectInfoDO.getId())).collect(Collectors.toList()))) {
                    // 创建ZK项目
                    String zkProjectNodePath = NodePathContant.ROOT_PATH + NodePathContant.ENV_PATH + NodePathContant.PATH_SUB + dbEnvId + NodePathContant.PROJECT_PATH + NodePathContant.PATH_SUB + dbProjectInfoDO.getId();
                    ZookeeperClientUtil.createNode(zkProjectNodePath, JSONObject.toJSONBytes(new NodeDTO(zkProjectNodePath, dbProjectInfoDO.getId(), DEFAULT_VERSION)), CreateMode.PERSISTENT);
                    for (ConfigInfoDO dbConfigInfoDO : getProConfigMap().get(dbProjectInfoDO.getId())) {
                        // 创建ZK配置
                        String zkConfigNodePath = NodePathContant.ROOT_PATH + NodePathContant.ENV_PATH + NodePathContant.PATH_SUB + dbEnvId + NodePathContant.PROJECT_PATH + NodePathContant.PATH_SUB + dbProjectInfoDO.getId() + NodePathContant.PATH_SUB + dbConfigInfoDO.getId();
                        ZookeeperClientUtil.createNode(zkConfigNodePath, JSONObject.toJSONBytes(new NodeDTO(zkConfigNodePath, dbConfigInfoDO.getId(), dbConfigInfoDO.getConfigFileVersion())), CreateMode.PERSISTENT);
                    }
                } else {
                    List<NodeDTO> zkConfigNodeList = ZookeeperClientUtil.getChildrenNodes(NodePathContant.ROOT_PATH + NodePathContant.ENV_PATH + NodePathContant.PATH_SUB + dbEnvId + NodePathContant.PROJECT_PATH + NodePathContant.PATH_SUB + dbProjectInfoDO.getId());
                    for (ConfigInfoDO dbConfigInfoDO : getProConfigMap().get(dbProjectInfoDO.getId())) {
                        if (CollectionUtils.isEmpty(zkConfigNodeList.stream().filter(s -> s.getData().equals(dbConfigInfoDO.getId())).collect(Collectors.toList()))) {
                            // 创建ZK配置
                            String zkConfigNodePath = NodePathContant.ROOT_PATH + NodePathContant.ENV_PATH + NodePathContant.PATH_SUB + dbEnvId + NodePathContant.PROJECT_PATH + NodePathContant.PATH_SUB + dbProjectInfoDO.getId() + NodePathContant.PATH_SUB + dbConfigInfoDO.getId();
                            ZookeeperClientUtil.createNode(zkConfigNodePath, JSONObject.toJSONBytes(new NodeDTO(zkConfigNodePath, dbConfigInfoDO.getId(), dbConfigInfoDO.getConfigFileVersion())), CreateMode.PERSISTENT);
                        } else {
                            // 比较ZK配置
                            for (NodeDTO zkConfigNodeDTO : zkConfigNodeList) {
                                if (!zkConfigNodeDTO.getData().equals(""+dbConfigInfoDO.getId())) {
                                    continue;
                                }
                                if (!zkConfigNodeDTO.getVersion().equals(dbConfigInfoDO.getConfigFileVersion())) {
                                    // 更新为最新
                                    String zkConfigNodePath = NodePathContant.ROOT_PATH + NodePathContant.ENV_PATH + NodePathContant.PATH_SUB + dbEnvId + NodePathContant.PROJECT_PATH + dbProjectInfoDO.getId() + NodePathContant.PATH_SUB + dbConfigInfoDO.getId();
                                    ZookeeperClientUtil.modifyNode(zkConfigNodePath, JSONObject.toJSONBytes(new NodeDTO(zkConfigNodePath, dbConfigInfoDO.getId(), dbConfigInfoDO.getConfigFileVersion())));
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
