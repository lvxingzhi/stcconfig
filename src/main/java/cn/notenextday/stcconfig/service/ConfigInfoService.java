package cn.notenextday.stcconfig.service;

import cn.notenextday.stcconfig.dao.ConfigInfoDao;
import cn.notenextday.stcconfig.dto.entity.ConfigInfoDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/29 16:04
 */
@Service
public class ConfigInfoService {

    @Resource
    private ConfigInfoDao configInfoDao;

    /**
     * 查询环境列表
     */
    public List<ConfigInfoDO> findListByProjectId(Integer projectId){
        return configInfoDao.findListByProjectId(projectId);
    }

}
