package cn.notenextday.stcconfigserver.dto.entity;

import java.util.Date;

public class EnvInfoDO {
    /**
     * ID
     * 表字段: id
     */
    private Integer id;

    /**
     * 环境名称
     * 表字段: env_name
     */
    private String envName;

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

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
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