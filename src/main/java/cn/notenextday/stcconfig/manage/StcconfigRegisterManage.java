package cn.notenextday.stcconfig.manage;

import cn.notenextday.stcconfig.dto.ConfigInfoDO;
import cn.notenextday.stcconfig.dto.EnvInfoDO;
import cn.notenextday.stcconfig.dto.NodeDTO;
import cn.notenextday.stcconfig.dto.ProjectInfoDO;
import cn.notenextday.stcconfig.service.ConfigInfoService;
import cn.notenextday.stcconfig.service.EnvInfoService;
import cn.notenextday.stcconfig.service.ProjectInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 管理器定义
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/28 11:30
 */
public abstract class StcconfigRegisterManage {
    private static final Logger logger = LoggerFactory.getLogger(StcconfigRegisterManage.class);
    @Resource
    private EnvInfoService envInfoService;
    @Resource
    private ProjectInfoService projectInfoService;
    @Resource
    private ConfigInfoService configInfoService;
    // 环境列表
    private List<NodeDTO> nodeDTOList = new ArrayList<>();
    // 环境-项目
    private Map<Integer, List<ProjectInfoDO>> envProjectMap = new HashMap<>();
    // 项目-文件
    private Map<Integer, List<ConfigInfoDO>> proConfigMap = new HashMap<>();

    public void init() {
        NodeDTO nodeDTO = getRootNode();
        if (Objects.isNull(nodeDTO)) {
            // 创建RootNode
            createRootNode();
        }
        // 查询环境
        List<EnvInfoDO> envInfoDOList = envInfoService.selectAll();
        if (CollectionUtils.isEmpty(envInfoDOList)) {
            logger.warn("[stcconfig配置初始化] 环境未配置");
            return;
        }
        // 查询项目
        for (EnvInfoDO envInfoDO : envInfoDOList) {
            List<ProjectInfoDO> projectInfoDOList = projectInfoService.findListByEnvId(envInfoDO.getId());
            if (CollectionUtils.isEmpty(projectInfoDOList)) {
                continue;
            }
            envProjectMap.put(envInfoDO.getId(), projectInfoDOList);
            for (ProjectInfoDO projectInfoDO : projectInfoDOList) {
                // 查询数据库配置文件
                List<ConfigInfoDO> configInfoDOList = configInfoService.findListByProjectId(projectInfoDO.getId());
                if(!CollectionUtils.isEmpty(configInfoDOList)){
                    proConfigMap.put(projectInfoDO.getId(), configInfoDOList);
                }
            }
        }
        // 扫描zk目录, 删除不存在的节点, 对比版本号更新/插入
        checkAliveStatus();
        buildNodes();
    }

    /**
     * 状态检查
     *
     * @return
     */
    public abstract boolean checkAliveStatus();

    /**
     * 获取stcconfig根节点
     *
     * @return
     */
    public abstract NodeDTO getRootNode();

    /**
     * 创建stcconfig根节点
     *
     * @return
     */
    public abstract boolean createRootNode();

    /**
     * 构建节点信息
     * @return
     */
    public abstract boolean buildNodes();

    public Map<Integer, List<ProjectInfoDO>> getEnvProjectMap() {
        return envProjectMap;
    }

    public Map<Integer, List<ConfigInfoDO>> getProConfigMap() {
        return proConfigMap;
    }
}
