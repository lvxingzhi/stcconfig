package cn.notenextday.stcconfigserver.manage.controller;

import cn.notenextday.stcconfigserver.dto.entity.EnvInfoDO;
import cn.notenextday.stcconfigserver.service.EnvInfoService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/8/2 11:44
 */
@Service
public class WebApiManage {

    @Resource
    private EnvInfoService envInfoService;

    /**
     * 查询环境列表
     */
    public String getEnvList() {
        List<EnvInfoDO> envList = envInfoService.selectAll();
        return JSON.toJSONString(envList);
    }

}
