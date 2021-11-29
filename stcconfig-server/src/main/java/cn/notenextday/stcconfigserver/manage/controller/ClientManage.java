package cn.notenextday.stcconfigserver.manage.controller;

import cn.notenextday.stcconfigserver.dto.ConfigDTO;
import cn.notenextday.stcconfigserver.util.TypeUtil;
import org.springframework.stereotype.Service;

/**
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/8/2 11:44
 */
@Service
public class ClientManage {

    /**
     * /stcconfig/env/envId/project/projectId/configId
     *
     * @param path
     * @return
     */
    public ConfigDTO splitForConfigDTO(String path) {
        path = path.replace("/stcconfig/env", ""); // envId/project/projectId/configId
        path = path.replace("/project", ""); // envId/projectId/configId
        String[] idArray = path.split("/");
        Integer envId = TypeUtil.stringToInt(idArray[1]);
        Integer projectId = TypeUtil.stringToInt(idArray[2]);
        Integer configId = TypeUtil.stringToInt(idArray[3]);
        return new ConfigDTO(envId, projectId, configId);
    }

}
