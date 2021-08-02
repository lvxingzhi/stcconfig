package cn.notenextday.stcconfig.dao;

import cn.notenextday.stcconfig.dto.ProjectInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


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
}