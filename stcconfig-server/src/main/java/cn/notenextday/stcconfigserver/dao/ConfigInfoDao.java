package cn.notenextday.stcconfigserver.dao;

import cn.notenextday.stcconfigserver.dto.entity.ConfigInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface ConfigInfoDao {
    /**
     *
     * @mbggenerated
     */
    int insert(ConfigInfoDO configInfoDO);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(ConfigInfoDO configInfoDO);

    /**
     *
     * @mbggenerated
     */
    ConfigInfoDO selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ConfigInfoDO configInfoDO);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(ConfigInfoDO configInfoDO);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ConfigInfoDO configInfoDO);

    /**
     *
     */
    List<ConfigInfoDO> findListByProjectId(Integer projectId);

    /**
     *
     */
    List<ConfigInfoDO> findListByCondition(Map<String, Object> condition);

}