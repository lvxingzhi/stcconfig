package cn.notenextday.stcconfigserver.dto.entity;

import lombok.Data;

import java.util.Date;

@Data
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

    /**
     * 父节点
     */
    private Integer parentId;

}