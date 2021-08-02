package cn.notenextday.stcconfig.service;

import cn.notenextday.stcconfig.dao.EnvInfoDao;
import cn.notenextday.stcconfig.dao.ProjectInfoDao;
import cn.notenextday.stcconfig.dto.EnvInfoDO;
import cn.notenextday.stcconfig.dto.ProjectInfoDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public List<ProjectInfoDO> findListByEnvId(Integer envId){
        return projectInfoDao.findListByEnvId(envId);
    }
}
