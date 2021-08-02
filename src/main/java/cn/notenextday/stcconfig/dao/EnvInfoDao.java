package cn.notenextday.stcconfig.dao;


import cn.notenextday.stcconfig.dto.EnvInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnvInfoDao {
    /**
     *
     * @mbggenerated
     */
    int insert(EnvInfoDO record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(EnvInfoDO record);

    /**
     *
     * @mbggenerated
     */
    EnvInfoDO selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(EnvInfoDO record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(EnvInfoDO record);

    /**
     *
     * @mbggenerated
     */
    List<EnvInfoDO> selectAll();
}