package cn.notenextday.stcconfigserver.dto.entity;

import com.alibaba.fastjson.annotation.JSONField;
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
     * 0 未删除 1 已删除
     * 表字段: delete_flag
     */
    private Integer deleteFlag;

    /**
     * 0 有效 1 失效
     * 表字段: delete_flag
     */
    private Integer disableFlag;

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

    /**
     * 父节点
     */
    private Integer parentId;

}