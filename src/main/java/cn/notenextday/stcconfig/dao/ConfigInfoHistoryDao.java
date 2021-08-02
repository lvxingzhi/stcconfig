package cn.notenextday.stcconfig.dao;

import cn.notenextday.stcconfig.dto.ConfigInfoHistoryDO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ConfigInfoHistoryDao {
    /**
     *
     * @mbggenerated
     */
    int insert(ConfigInfoHistoryDO record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(ConfigInfoHistoryDO record);

    /**
     *
     * @mbggenerated
     */
    ConfigInfoHistoryDO selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ConfigInfoHistoryDO record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(ConfigInfoHistoryDO record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ConfigInfoHistoryDO record);
}