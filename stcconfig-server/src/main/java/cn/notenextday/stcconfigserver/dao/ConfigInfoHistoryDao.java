package cn.notenextday.stcconfigserver.dao;

import cn.notenextday.stcconfigserver.dto.entity.ConfigInfoHistoryDO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ConfigInfoHistoryDao {
    /**
     *
     * @mbggenerated
     */
    int insert(ConfigInfoHistoryDO configInfoHistoryDO);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(ConfigInfoHistoryDO configInfoHistoryDO);

    /**
     *
     * @mbggenerated
     */
    ConfigInfoHistoryDO selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ConfigInfoHistoryDO configInfoHistoryDO);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(ConfigInfoHistoryDO configInfoHistoryDO);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ConfigInfoHistoryDO configInfoHistoryDO);
}