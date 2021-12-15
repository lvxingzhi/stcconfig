package cn.notenextday.stcconfigserver.manage.controller;

import cn.notenextday.stcconfigserver.dto.entity.ConfigInfoDO;
import cn.notenextday.stcconfigserver.dto.entity.EnvInfoDO;
import cn.notenextday.stcconfigserver.dto.entity.ProjectInfoDO;
import cn.notenextday.stcconfigserver.enums.ConfigFileTypeEnum;
import cn.notenextday.stcconfigserver.service.ConfigInfoService;
import cn.notenextday.stcconfigserver.service.EnvInfoService;
import cn.notenextday.stcconfigserver.service.ProjectInfoService;
import cn.notenextday.stcconfigserver.util.TypeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/8/2 11:44
 */
@Service
public class WebApiManage {

    @Resource
    private EnvInfoService envInfoService;
    @Resource
    private ConfigInfoService configInfoService;
    @Resource
    private ProjectInfoService projectInfoService;

    /**
     * 查询环境列表
     */
    public String getEnvList() {
        List<EnvInfoDO> envList = envInfoService.selectAll();
        return JSON.toJSONString(envList);
    }

    /**
     * 查询环境详情
     */
    public String getEnvInfo(JSONObject data) {
        EnvInfoDO envInfoDO = JSON.parseObject(data.toJSONString(), EnvInfoDO.class);
        EnvInfoDO envInfo = envInfoService.findById(envInfoDO.getId());
        return JSON.toJSONString(envInfo);
    }

    /**
     * 添加环境
     */
    public Integer addEnv(JSONObject data) {
        EnvInfoDO envInfoDO = JSON.parseObject(data.toJSONString(), EnvInfoDO.class);
        Integer id = envInfoService.add(envInfoDO);
        return id;
    }

    /**
     * 修改环境
     */
    public Integer updateEnv(JSONObject data) {
        EnvInfoDO envInfoDO = JSON.parseObject(data.toJSONString(), EnvInfoDO.class);
        Integer id = envInfoService.update(envInfoDO);
        return id;
    }

    /**
     * 删除环境
     */
    public Integer deleteEnv(JSONObject data) {
        EnvInfoDO envInfoDO = JSON.parseObject(data.toJSONString(), EnvInfoDO.class);
        Integer id = envInfoService.delete(envInfoDO.getId());
        return id;
    }

    /**
     * 查询配置文件列表
     */
    public String getConfigList(JSONObject data) {
        Map<String, Object> condition = new HashMap<>();
        if (Objects.nonNull(data.get("projectId")) && Strings.isNotEmpty((String) data.get("projectId"))) {
            condition.put("projectId", data.get("projectId"));
        }
        if (Objects.nonNull(data.get("configFileName")) && Strings.isNotEmpty((String) data.get("configFileName"))) {
            condition.put("configFileName", data.get("configFileName"));
        }
        List<ConfigInfoDO> configList = configInfoService.findListByCondition(condition);
        return JSON.toJSONString(configList);
    }

    /**
     * 查询配置文件详情
     */
    public String getConfigInfo(JSONObject data) {
        ConfigInfoDO configInfoDO = JSON.parseObject(data.toJSONString(), ConfigInfoDO.class);
        ConfigInfoDO configInfo = configInfoService.findById(configInfoDO.getId());
        return JSON.toJSONString(configInfo);
    }

    /**
     * 添加项目
     */
    public Integer addConfig(JSONObject data) {
        ConfigInfoDO configInfoDO = JSON.parseObject(data.toJSONString(), ConfigInfoDO.class);
        configInfoDO.setConfigFileType(ConfigFileTypeEnum.getEnumByCode(TypeUtil.stringToInt(configInfoDO.getConfigType())).getName());
        configInfoDO.setConfigFileVersion(1);
        configInfoDO.setCreateTime(new Date());
        configInfoDO.setConfigFileVersionPrevious(1);
        if(Objects.isNull(configInfoDO.getConfigIndex())){
            configInfoDO.setConfigIndex(1);
        }
        Integer id = configInfoService.add(configInfoDO);
        return id;
    }

    /**
     * 添加项目
     */
    public Integer updateConfig(JSONObject data) {
        ConfigInfoDO configInfoDO = JSON.parseObject(data.toJSONString(), ConfigInfoDO.class);
        ConfigInfoDO existConfigInfo = configInfoService.findById(configInfoDO.getId());
        configInfoDO.setConfigFileVersion(existConfigInfo.getConfigFileVersion() + 1);
        configInfoDO.setConfigFileVersionPrevious(existConfigInfo.getConfigFileVersion());
        configInfoDO.setUpdateTime(new Date());
        configInfoDO.setConfigFileType(ConfigFileTypeEnum.getEnumByCode(TypeUtil.stringToInt(configInfoDO.getConfigType())).getName());
        Integer id = configInfoService.update(configInfoDO);
        return id;
    }

    /**
     * 添加项目
     */
    public Integer deleteConfig(JSONObject data) {
        ConfigInfoDO configInfoDO = JSON.parseObject(data.toJSONString(), ConfigInfoDO.class);
        Integer id = configInfoService.delete(configInfoDO.getId());
        return id;
    }

    /**
     * 查询项目列表
     */
    public String getProjectList(JSONObject data) {
        Map<String, Object> condition = new HashMap<>();
        if (Objects.nonNull(data.get("envId"))&& Strings.isNotEmpty((String)data.get("envId"))) {
            condition.put("envId", data.get("envId"));
        }
        List<ProjectInfoDO> projectList = projectInfoService.findListByCondition(condition);
        return JSON.toJSONString(projectList);
    }

    /**
     * 查询项目列表
     */
    public String getProjectInfo(JSONObject data) {
        ProjectInfoDO projectInfoDO = JSON.parseObject(data.toJSONString(), ProjectInfoDO.class);
        ProjectInfoDO projectInfo = projectInfoService.findById(projectInfoDO.getId());
        return JSON.toJSONString(projectInfo);
    }

    /**
     * 添加项目
     */
    public Integer addProject(JSONObject data) {
        ProjectInfoDO projectInfoDO = JSON.parseObject(data.toJSONString(), ProjectInfoDO.class);
        Integer id = projectInfoService.add(projectInfoDO);
        return id;
    }

    /**
     * 修改项目
     */
    public Integer updateProject(JSONObject data) {
        ProjectInfoDO projectInfoDO = JSON.parseObject(data.toJSONString(), ProjectInfoDO.class);
        Integer id = projectInfoService.update(projectInfoDO);
        return id;
    }

    /**
     * 删除项目
     */
    public Integer deleteProject(JSONObject data) {
        ProjectInfoDO projectInfoDO = JSON.parseObject(data.toJSONString(), ProjectInfoDO.class);
        Integer id = projectInfoService.delete(projectInfoDO.getId());
        return id;
    }

}
