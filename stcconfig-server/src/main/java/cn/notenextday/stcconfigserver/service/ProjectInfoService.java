package cn.notenextday.stcconfigserver.service;

import cn.notenextday.stcconfigserver.dao.ProjectInfoDao;
import cn.notenextday.stcconfigserver.dto.entity.ProjectInfoDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/29 16:05
 */
@Service
public class ProjectInfoService {
    @Resource
    private ProjectInfoDao projectInfoDao;

    /**
     * 查询环境列表
     */
    public List<ProjectInfoDO> findListByEnvId(Integer envId) {
        return projectInfoDao.findListByEnvId(envId);
    }

    /**
     * 查询环境列表
     */
    public List<ProjectInfoDO> findListByCondition(Map<String, Object> condition) {
        return projectInfoDao.findListByCondition(condition);
    }

    /**
     * 添加
     */
    public Integer add(ProjectInfoDO projectInfoDO) {
        return projectInfoDao.insertSelective(projectInfoDO);
    }

    /**
     * 修改
     */
    public Integer update(ProjectInfoDO projectInfoDO) {
        return projectInfoDao.updateByPrimaryKeySelective(projectInfoDO);
    }

    /**
     * 删除
     */
    public Integer delete(Integer projectId) {
        ProjectInfoDO projectInfoDO = new ProjectInfoDO();
        projectInfoDO.setId(projectId);
        projectInfoDO.setDeleteFlag(1);
        projectInfoDO.setUpdateTime(new Date());
        return projectInfoDao.updateByPrimaryKeySelective(projectInfoDO);
    }

    /**
     * 查询环境详情
     */
    public ProjectInfoDO findById(Integer projectId) {
        return projectInfoDao.selectByPrimaryKey(projectId);
    }
}
