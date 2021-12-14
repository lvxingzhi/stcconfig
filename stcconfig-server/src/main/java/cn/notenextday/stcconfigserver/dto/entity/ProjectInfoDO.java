package cn.notenextday.stcconfigserver.dto.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * 项目表
 */
@Data
public class ProjectInfoDO {
    /**
     * ID
     * 表字段: id
     */
    private Integer id;

    /**
     * 项目名称
     * 表字段: project_name
     */
    private String projectName;

    /**
     * 环境ID
     * 表字段: env_id
     */
    private Integer envId;

    /**
     * 0 有效 1 失效
     * 表字段: delete_flag
     */
    private Integer deleteFlag;

    /**
     * 创建时间
     * 表字段: create_time
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     * 表字段: update_time
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}