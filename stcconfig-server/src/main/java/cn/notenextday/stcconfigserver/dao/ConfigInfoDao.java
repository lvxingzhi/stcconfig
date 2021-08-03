package cn.notenextday.stcconfigserver.dao;

import cn.notenextday.stcconfigserver.dto.entity.ConfigInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ConfigInfoDao {
    /**
     *
     * @mbggenerated
     */
    int insert(ConfigInfoDO record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(ConfigInfoDO record);

    /**
     *
     * @mbggenerated
     */
    ConfigInfoDO selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ConfigInfoDO record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(ConfigInfoDO record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ConfigInfoDO record);

    /**
     *
     */
    List<ConfigInfoDO> findListByProjectId(Integer projectId);
}