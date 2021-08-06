package cn.notenextday.stcconfigserver.dao;


import cn.notenextday.stcconfigserver.dto.entity.EnvInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnvInfoDao {
    /**
     *
     * @mbggenerated
     */
    int insert(EnvInfoDO envInfoDO);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(EnvInfoDO envInfoDO);

    /**
     *
     * @mbggenerated
     */
    EnvInfoDO selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(EnvInfoDO envInfoDO);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(EnvInfoDO envInfoDO);

    /**
     *
     * @mbggenerated
     */
    List<EnvInfoDO> selectAll();
}