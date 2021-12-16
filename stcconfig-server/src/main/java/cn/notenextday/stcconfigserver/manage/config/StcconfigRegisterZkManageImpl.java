package cn.notenextday.stcconfigserver.manage.config;

import cn.notenextday.stcconfigserver.constant.HttpConstant;
import cn.notenextday.stcconfigserver.constant.NodePathContant;
import cn.notenextday.stcconfigserver.dto.NodeDTO;
import cn.notenextday.stcconfigserver.dto.entity.ConfigInfoDO;
import cn.notenextday.stcconfigserver.dto.entity.ProjectInfoDO;
import cn.notenextday.stcconfigserver.util.TypeUtil;
import cn.notenextday.stcconfigserver.util.ZookeeperClientUtil;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.util.Strings;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
@Repository(value = "stcconfigRegisterManage")
public class StcconfigRegisterZkManageImpl extends StcconfigRegisterManage {
    private static final Logger logger = LoggerFactory.getLogger(StcconfigRegisterZkManageImpl.class);
    private static final Integer DEFAULT_VERSION = 1;
    private static final String ROOT_DATA = "{\"path\":\"/stcconfig\",\"data\":\"stcconfig\"}";
    @Value("${stcconfig.server.url}")
    private String stcconfigUrl;
    @Value("${stcconfig.server.port}")
    private String stcconfigPort;

    /**
     * 检测zk存活状态
     *
     * @return
     */
    @Override
    public boolean checkAliveStatus() {
        try {
            ZookeeperClientUtil.getNode(NodePathContant.ROOT_PATH, null);
            return true;
        } catch (Exception e) {
            logger.error("[zookeeper] 检查存活状态异常", e);
        }
        return false;
    }

    /**
     * 获取根节点 [/stcconfig]
     *
     * @return
     */
    @Override
    public NodeDTO getRootNode() {
        if (ZookeeperClientUtil.existsNode(NodePathContant.ROOT_PATH)) {
            return ZookeeperClientUtil.getNode(NodePathContant.ROOT_PATH, null);
        }
        return null;
    }

    /**
     * 创建根节点 [/stcconfig]
     *
     * @return
     */
    @Override
    public void createRootNode() {
        if (!Objects.isNull(getRootNode())) {
            return;
        }
        ZookeeperClientUtil.createNode(NodePathContant.ROOT_PATH, ROOT_DATA.getBytes(StandardCharsets.UTF_8), CreateMode.PERSISTENT);
    }

    /**
     * 创建配置树
     *
     * @return
     */
    @Override
    public void buildNodes() {
        if (!checkAliveStatus()) {
            logger.error("[zookeeper] zk状态未存活,检查zk运行状态");
            return;
        }
        // 1,创建根节点
        createRootNode();
        // 2,获取ENV节点列表
        List<NodeDTO> zkEnvNodeList = getZkEnvNodeList();
        // 3,删除ZK多余的节点[基于DB数据]
        deleteZkNodeList(zkEnvNodeList);
        // 4,创建ZK没有的节点[基于DB数据]
        createZkNodeList(zkEnvNodeList);
    }

    /**
     * 获取zk路径工具
     */
    private String getZkPathValue(String envName, String projectName, String configName) {
        String path = "";
        if (Strings.isEmpty(envName)) {
            return NodePathContant.ENV_PATH_ALL;
        }
        path = NodePathContant.ENV_PATH_ALL + NodePathContant.PATH_SUB + envName;
        if (Strings.isEmpty(projectName)) {
            return path;
        }
        path = NodePathContant.ENV_PATH_ALL + NodePathContant.PATH_SUB + envName + NodePathContant.PROJECT_PATH + NodePathContant.PATH_SUB + projectName;
        if (Strings.isEmpty(configName)) {
            return path;
        }
        path = NodePathContant.ENV_PATH_ALL + NodePathContant.PATH_SUB + envName + NodePathContant.PROJECT_PATH + NodePathContant.PATH_SUB + projectName + NodePathContant.PATH_SUB + configName;
        return path;

    }

    /**
     * 获取zk路径工具
     */
    private String getZkPathValue(Integer envName, Integer projectName, Integer configName) {
        return getZkPathValue(TypeUtil.intToString(envName), TypeUtil.intToString(projectName), TypeUtil.intToString(configName));
    }

    /**
     * ENV节点列表
     *
     * @return
     */
    private List<NodeDTO> getZkEnvNodeList() {
        // 判断/env是否创建
        if (!ZookeeperClientUtil.existsNode(NodePathContant.ENV_PATH_ALL)) {
            ZookeeperClientUtil.createNode(NodePathContant.ENV_PATH_ALL, JSON.toJSONBytes(new NodeDTO("", "", DEFAULT_VERSION)), CreateMode.PERSISTENT);
        }
        // 获取ZK环境列表
        return ZookeeperClientUtil.getChildrenNodes(NodePathContant.ENV_PATH_ALL);
    }

    /**
     * 删除ZK多余的节点[基于DB数据]
     */
    private void deleteZkNodeList(List<NodeDTO> zkEnvNodeList) {
        // 以ZK为基准对比DB, 没有则删除ZK, (没有配置版本对比)
        for (NodeDTO zkEnvNodeDTO : zkEnvNodeList) {
            if (!getEnvProjectMap().keySet().contains(Integer.parseInt(zkEnvNodeDTO.getData()))) {
                // 删除ZK环境
                ZookeeperClientUtil.deleteNode(zkEnvNodeDTO.getPath());
            }

            // 判断/project是否创建
            if (!ZookeeperClientUtil.existsNode(zkEnvNodeDTO.getPath() + NodePathContant.PROJECT_PATH)) {
                ZookeeperClientUtil.createNode(zkEnvNodeDTO.getPath() + NodePathContant.PROJECT_PATH, JSON.toJSONBytes(new NodeDTO(zkEnvNodeDTO.getPath() + NodePathContant.PROJECT_PATH, "env", DEFAULT_VERSION)), CreateMode.PERSISTENT);
            }
            // 获取ZK项目列表
            List<NodeDTO> zkProjectNodeList = ZookeeperClientUtil.getChildrenNodes(zkEnvNodeDTO.getPath() + NodePathContant.PROJECT_PATH);
            for (NodeDTO zkProNodeDTO : zkProjectNodeList) {
                if (CollectionUtils.isEmpty(getEnvProjectMap().get(Integer.parseInt(zkEnvNodeDTO.getData())).stream().filter(s -> s.getId().equals(Integer.parseInt(zkProNodeDTO.getData()))).collect(Collectors.toList()))) {
                    // 删除ZK项目
                    ZookeeperClientUtil.deleteNode(zkProNodeDTO.getPath());
                }

                // 获取ZK配置列表
                List<NodeDTO> zkConfigNodeList = ZookeeperClientUtil.getChildrenNodes(zkProNodeDTO.getPath());
                for (NodeDTO zkConfigNodeDTO : zkConfigNodeList) {
                    if (CollectionUtils.isEmpty(getProConfigMap().get(Integer.parseInt(zkProNodeDTO.getData())).stream().filter(s -> s.getId().equals(Integer.parseInt(zkConfigNodeDTO.getData()))).collect(Collectors.toList()))) {
                        // 删除ZK配置
                        ZookeeperClientUtil.deleteNode(zkConfigNodeDTO.getPath());
                    }
                }
            }
        }
    }

    /**
     * 创建ZK没有的节点[基于DB数据]
     */
    private void createZkNodeList(List<NodeDTO> zkEnvNodeList) {
        // 以DB为基准对比ZK, 没有则新增ZK, (配置版本对比更新)
        for (Integer dbEnvId : getEnvProjectMap().keySet()) {
            if (CollectionUtils.isEmpty(zkEnvNodeList.stream().filter(s -> s.getPath().endsWith(NodePathContant.PATH_SUB + dbEnvId)).collect(Collectors.toList()))) {
                // 创建ZK环境
                String zkEnvNodePath = getZkPathValue(dbEnvId, null, null);
                ZookeeperClientUtil.createNode(zkEnvNodePath, JSON.toJSONBytes(new NodeDTO(zkEnvNodePath, dbEnvId, DEFAULT_VERSION)), CreateMode.PERSISTENT);
            }

            // 判断/project是否创建
            if (!ZookeeperClientUtil.existsNode(NodePathContant.ENV_PATH_ALL + NodePathContant.PATH_SUB + dbEnvId + NodePathContant.PROJECT_PATH)) {
                ZookeeperClientUtil.createNode(NodePathContant.ENV_PATH_ALL + NodePathContant.PATH_SUB + dbEnvId + NodePathContant.PROJECT_PATH, JSON.toJSONBytes(new NodeDTO(NodePathContant.ROOT_PATH + NodePathContant.ENV_PATH + NodePathContant.PATH_SUB + dbEnvId + NodePathContant.PROJECT_PATH, "env", DEFAULT_VERSION)), CreateMode.PERSISTENT);
            }
            // 获取ZK项目列表
            List<NodeDTO> zkProjectNodeList = ZookeeperClientUtil.getChildrenNodes(NodePathContant.ENV_PATH_ALL + NodePathContant.PATH_SUB + dbEnvId + NodePathContant.PROJECT_PATH);
            for (ProjectInfoDO dbProjectInfoDO : getEnvProjectMap().get(dbEnvId)) {
                if (CollectionUtils.isEmpty(zkProjectNodeList.stream().filter(s -> s.getPath().endsWith(NodePathContant.PATH_SUB + dbProjectInfoDO.getId())).collect(Collectors.toList()))) {
                    // 创建ZK项目
                    String zkProjectNodePath = getZkPathValue(dbEnvId, dbProjectInfoDO.getId(), null);
                    ZookeeperClientUtil.createNode(zkProjectNodePath, JSON.toJSONBytes(new NodeDTO(zkProjectNodePath, dbProjectInfoDO.getId(), DEFAULT_VERSION)), CreateMode.PERSISTENT);
                    if(CollectionUtils.isEmpty(getProConfigMap().get(dbProjectInfoDO.getId()))){
                        continue;
                    }
                    for (ConfigInfoDO dbConfigInfoDO : getProConfigMap().get(dbProjectInfoDO.getId())) {
                        // 创建ZK配置
                        String zkConfigNodePath = getZkPathValue(dbEnvId, dbProjectInfoDO.getId(), dbConfigInfoDO.getId());
                        ZookeeperClientUtil.createNode(zkConfigNodePath, JSON.toJSONBytes(new NodeDTO(HttpConstant.getUrl(stcconfigUrl, stcconfigPort, zkConfigNodePath), dbConfigInfoDO.getId(), dbConfigInfoDO.getConfigFileVersion(), dbConfigInfoDO.getConfigFileName())), CreateMode.PERSISTENT);
                    }
                } else {
                    List<NodeDTO> zkConfigNodeList = ZookeeperClientUtil.getChildrenNodes(getZkPathValue(dbEnvId, dbProjectInfoDO.getId(), null));
                    if(CollectionUtils.isEmpty(getProConfigMap().get(dbProjectInfoDO.getId()))){
                        continue;
                    }
                    for (ConfigInfoDO dbConfigInfoDO : getProConfigMap().get(dbProjectInfoDO.getId())) {
                        if (CollectionUtils.isEmpty(zkConfigNodeList.stream().filter(s -> s.getData().equals(TypeUtil.intToString(dbConfigInfoDO.getId()))).collect(Collectors.toList()))) {
                            // 创建ZK配置
                            String zkConfigNodePath = getZkPathValue(dbEnvId, dbProjectInfoDO.getId(), dbConfigInfoDO.getId());
                            ZookeeperClientUtil.createNode(zkConfigNodePath, JSON.toJSONBytes(new NodeDTO(HttpConstant.getUrl(stcconfigUrl, stcconfigPort, zkConfigNodePath), dbConfigInfoDO.getId(), dbConfigInfoDO.getConfigFileVersion(), dbConfigInfoDO.getConfigFileName())), CreateMode.PERSISTENT);
                        } else {
                            // 比较ZK配置
                            for (NodeDTO zkConfigNodeDTO : zkConfigNodeList) {
                                if (!zkConfigNodeDTO.getData().equals("" + dbConfigInfoDO.getId())) {
                                    continue;
                                }
                                if (!zkConfigNodeDTO.getVersion().equals(dbConfigInfoDO.getConfigFileVersion())) {
                                    // 更新为最新
                                    String zkConfigNodePath = getZkPathValue(dbEnvId, dbProjectInfoDO.getId(), dbConfigInfoDO.getId());
                                    ZookeeperClientUtil.modifyNode(zkConfigNodePath, JSON.toJSONBytes(new NodeDTO(HttpConstant.getUrl(stcconfigUrl, stcconfigPort, zkConfigNodePath), dbConfigInfoDO.getId(), dbConfigInfoDO.getConfigFileVersion(), dbConfigInfoDO.getConfigFileName())));
                                }
                            }
                        }
                    }
                }
                // 项目版本更新, 客户端监听项目版本
                NodeDTO nodeDTO = ZookeeperClientUtil.getNode(getZkPathValue(dbEnvId, dbProjectInfoDO.getId(), null), null);
                nodeDTO.setVersion(incrementVersion(nodeDTO.getVersion()));
                ZookeeperClientUtil.modifyNode(nodeDTO.getPath(), JSON.toJSONBytes(nodeDTO));
            }
        }
    }

    /**
     * 自增版本号
     *
     * @return
     */
    private Integer incrementVersion(Integer version) {
        return version + 1;
    }

    @Override
    public void run(String... args) throws Exception {
        this.init();
    }
}
