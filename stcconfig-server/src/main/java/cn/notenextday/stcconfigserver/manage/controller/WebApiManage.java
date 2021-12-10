package cn.notenextday.stcconfigserver.manage.controller;
import java.util.Date;

import cn.notenextday.stcconfigserver.dto.entity.ConfigInfoDO;
import cn.notenextday.stcconfigserver.dto.entity.EnvInfoDO;
import cn.notenextday.stcconfigserver.service.ConfigInfoService;
import cn.notenextday.stcconfigserver.service.EnvInfoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<ConfigInfoDO> configList = configInfoService.findListByCondition(condition);
        return JSON.toJSONString(configList);
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
