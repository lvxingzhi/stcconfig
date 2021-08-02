package cn.notenextday.stcconfig.dto.entity;

import java.util.Date;

/**
 * 用户表实体
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}