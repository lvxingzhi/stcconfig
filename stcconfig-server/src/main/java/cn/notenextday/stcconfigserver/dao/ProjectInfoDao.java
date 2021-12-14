package cn.notenextday.stcconfigserver.dao;

import cn.notenextday.stcconfigserver.dto.entity.ConfigInfoDO;
import cn.notenextday.stcconfigserver.dto.entity.ProjectInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface ProjectInfoDao {
    /**
     *
     * @mbggenerated
     */
    int insert(ProjectInfoDO record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(ProjectInfoDO record);

    /**
     *
     * @mbggenerated
     */
    ProjectInfoDO selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ProjectInfoDO record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ProjectInfoDO record);

    /**
     *
     * @param envId
     * @return
     */
    List<ProjectInfoDO> findListByEnvId(Integer envId);

    /**
     *
     */
    List<ProjectInfoDO> findListByCondition(Map<String, Object> condition);
}