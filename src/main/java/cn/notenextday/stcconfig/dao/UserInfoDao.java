package cn.notenextday.stcconfig.dao;

import cn.notenextday.stcconfig.dto.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserInfoDao {
    /**
     * @mbggenerated
     */
    int insert(UserInfoDO record);

    /**
     * @mbggenerated
     */
    int insertSelective(UserInfoDO record);

    /**
     * @mbggenerated
     */
    UserInfoDO selectByPrimaryKey(Integer id);

    /**
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserInfoDO record);

    /**
     * @mbggenerated
     */
    int updateByPrimaryKey(UserInfoDO record);
}