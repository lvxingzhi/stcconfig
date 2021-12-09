package cn.notenextday.stcconfigserver.service;

import cn.notenextday.stcconfigserver.dao.ConfigInfoDao;
import cn.notenextday.stcconfigserver.dto.entity.ConfigInfoDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Config-service
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/29 16:04
 */
@Service
public class ConfigInfoService {

    @Resource
    private ConfigInfoDao configInfoDao;

    /**
     * 查询配置列表
     */
    public List<ConfigInfoDO> findListByProjectId(Integer projectId) {
        return configInfoDao.findListByProjectId(projectId);
    }

    /**
     * 查询配置详情
     */
    public ConfigInfoDO findById(Integer configId) {
        return configInfoDao.selectByPrimaryKey(configId);
    }

}
