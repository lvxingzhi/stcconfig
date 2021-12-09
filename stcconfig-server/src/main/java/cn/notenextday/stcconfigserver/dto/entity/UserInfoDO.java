package cn.notenextday.stcconfigserver.dto.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户表实体
 */
@Data
public class UserInfoDO {
    /**
     * ID
     * 表字段: id
     */
    private Integer id;

    /**
     * 账户名
     * 表字段: account_name
     */
    private String accountName;

    /**
     * 账户密码
     * 表字段: account_password
     */
    private String accountPassword;

    /**
     * 0 有效 1 失效
     * 表字段: delete_flag
     */
    private Integer deleteFlag;

    /**
     * 创建时间
     * 表字段: create_time
     */
    private Date createTime;

    /**
     * 修改时间
     * 表字段: update_time
     */
    private Date updateTime;
}