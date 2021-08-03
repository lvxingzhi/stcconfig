package cn.notenextday.stcconfigserver.dao;

import cn.notenextday.stcconfigserver.dto.entity.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;

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