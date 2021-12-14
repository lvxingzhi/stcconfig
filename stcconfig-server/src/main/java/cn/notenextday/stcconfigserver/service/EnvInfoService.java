package cn.notenextday.stcconfigserver.service;

import cn.notenextday.stcconfigserver.dao.EnvInfoDao;
import cn.notenextday.stcconfigserver.dto.entity.EnvInfoDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/29 16:04
 */
@Service
public class EnvInfoService {
    @Resource
    private EnvInfoDao envInfoDao;

    /**
     * 查询环境列表(生效)
     */
    public List<EnvInfoDO> selectAllEnable() {
        return envInfoDao.selectAllEnable();
    }

    /**
     * 查询环境列表
     */
    public List<EnvInfoDO> selectAll() {
        return envInfoDao.selectAll();
    }

    /**
     * 添加
     */
    public Integer add(EnvInfoDO envInfoDO) {
        return envInfoDao.insertSelective(envInfoDO);
    }

    /**
     * 修改
     */
    public Integer update(EnvInfoDO envInfoDO) {
        return envInfoDao.updateByPrimaryKeySelective(envInfoDO);
    }

    /**
     * 删除
     */
    public Integer delete(Integer envId) {
        EnvInfoDO envInfoDO = new EnvInfoDO();
        envInfoDO.setId(envId);
        envInfoDO.setDeleteFlag(1);
        envInfoDO.setUpdateTime(new Date());
        return envInfoDao.updateByPrimaryKeySelective(envInfoDO);
    }

    /**
     * 查询环境详情
     */
    public EnvInfoDO findById(Integer envId) {
        return envInfoDao.selectByPrimaryKey(envId);
    }

}
