package cn.notenextday.stcconfigserver.manage.config;

import cn.notenextday.stcconfigserver.dto.NodeDTO;
import cn.notenextday.stcconfigserver.dto.entity.ConfigInfoDO;
import cn.notenextday.stcconfigserver.dto.entity.EnvInfoDO;
import cn.notenextday.stcconfigserver.dto.entity.ProjectInfoDO;
import cn.notenextday.stcconfigserver.service.ConfigInfoService;
import cn.notenextday.stcconfigserver.service.EnvInfoService;
import cn.notenextday.stcconfigserver.service.ProjectInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理器定义
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/28 11:30
 */
public abstract class StcconfigRegisterManage implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(StcconfigRegisterManage.class);
    @Resource
    private EnvInfoService envInfoService;
    @Resource
    private ProjectInfoService projectInfoService;
    @Resource
    private ConfigInfoService configInfoService;
    // 环境-项目
    private Map<Integer, List<ProjectInfoDO>> envProjectMap = new HashMap<>();
    // 项目-文件
    private Map<Integer, List<ConfigInfoDO>> proConfigMap = new HashMap<>();

    public void init() {
        // 创建RootNode
        createRootNode();
        // 查询环境
        List<EnvInfoDO> envInfoDOList = envInfoService.selectAll();
        if (CollectionUtils.isEmpty(envInfoDOList)) {
            logger.warn("[stcconfig配置初始化] DB环境未配置");
            return;
        }
        // 查询DB项目
        for (EnvInfoDO envInfoDO : envInfoDOList) {
            List<ProjectInfoDO> projectInfoDOList = projectInfoService.findListByEnvId(envInfoDO.getId());
            if (CollectionUtils.isEmpty(projectInfoDOList)) {
                continue;
            }
            envProjectMap.put(envInfoDO.getId(), projectInfoDOList);
            for (ProjectInfoDO projectInfoDO : projectInfoDOList) {
                // 查询DB配置
                List<ConfigInfoDO> configInfoDOList = configInfoService.findListByProjectId(projectInfoDO.getId());
                if (!CollectionUtils.isEmpty(configInfoDOList)) {
                    proConfigMap.put(projectInfoDO.getId(), configInfoDOList);
                }
            }
        }// 构建节点树
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
    public abstract void createRootNode();

    /**
     * 构建节点信息
     *
     * @return
     */
    public abstract void buildNodes();

    public Map<Integer, List<ProjectInfoDO>> getEnvProjectMap() {
        return envProjectMap;
    }

    public Map<Integer, List<ConfigInfoDO>> getProConfigMap() {
        return proConfigMap;
    }
}
