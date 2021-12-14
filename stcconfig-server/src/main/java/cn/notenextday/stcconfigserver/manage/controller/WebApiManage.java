package cn.notenextday.stcconfigserver.manage.controller;

import cn.notenextday.stcconfigserver.dto.entity.ConfigInfoDO;
import cn.notenextday.stcconfigserver.dto.entity.EnvInfoDO;
import cn.notenextday.stcconfigserver.dto.entity.ProjectInfoDO;
import cn.notenextday.stcconfigserver.service.ConfigInfoService;
import cn.notenextday.stcconfigserver.service.EnvInfoService;
import cn.notenextday.stcconfigserver.service.ProjectInfoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
     * 添加环境
     */
    public Integer addEnv(JSONObject data) {
        EnvInfoDO envInfoDO = JSON.parseObject(data.toJSONString(), EnvInfoDO.class);
        Integer id = envInfoService.add(envInfoDO);
        return id;
    }

    /**
     * 查询配置文件列表
     */
    public String getConfigList(JSONObject data) {
        Map<String, Object> condition = new HashMap<>();
        if (Objects.nonNull(data.get("projectId"))) {
            condition.put("projectId", data.get("projectId"));
        }
        if (Objects.nonNull(data.get("configFileName"))) {
            condition.put("configFileName", data.get("configFileName"));
        }
        List<ConfigInfoDO> configList = configInfoService.findListByCondition(condition);
        return JSON.toJSONString(configList);
    }


    /**
     * 添加项目
     */
    public Integer addConfig(JSONObject data) {
        ConfigInfoDO configInfoDO = JSON.parseObject(data.toJSONString(), ConfigInfoDO.class);
        Integer id = configInfoService.add(configInfoDO);
        return id;
    }

    /**
     * 查询项目列表
     */
    public String getProjectList(JSONObject data) {
        Map<String, Object> condition = new HashMap<>();
        if (Objects.nonNull(data.get("envId"))) {
            condition.put("envId", data.get("envId"));
        }
        List<ProjectInfoDO> projectList = projectInfoService.findListByCondition(condition);
        return JSON.toJSONString(projectList);
    }

    /**
     * 添加项目
     */
    public Integer addProject(JSONObject data) {
        ProjectInfoDO projectInfoDO = JSON.parseObject(data.toJSONString(), ProjectInfoDO.class);
        Integer id = projectInfoService.add(projectInfoDO);
        return id;
    }

    public static void main(String[] args) {
        EnvInfoDO envInfoDO = new EnvInfoDO();
        envInfoDO.setId(0);
        envInfoDO.setEnvName("");
        envInfoDO.setDeleteFlag(0);
        envInfoDO.setCreateTime(new Date());
        envInfoDO.setUpdateTime(new Date());
        envInfoDO.setParentId(0);
        System.out.println(JSON.toJSONString(envInfoDO));
    }

}
