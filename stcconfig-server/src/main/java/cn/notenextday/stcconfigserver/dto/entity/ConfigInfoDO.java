package cn.notenextday.stcconfigserver.dto.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * 配置表
 */
@Data
public class ConfigInfoDO {
    /**
     * ID
     * 表字段: id
     */
    private Integer id;

    /**
     * 配置类型
     * 表字段: config_type
     */
    private String configType;

    /**
     * 配置文件名
     * 表字段: config_file_name
     */
    private String configFileName;

    /**
     * 配置文件后缀类型
     * 表字段: config_file_type
     */
    private String configFileType;

    /**
     * 配置文件版本号
     * 表字段: config_file_version
     */
    private Integer configFileVersion;

    /**
     * 配置文件上一版本号
     * 表字段: config_file_version_previous
     */
    private Integer configFileVersionPrevious;

    /**
     * 配置文件次序
     * 表字段: config_index
     */
    private Integer configIndex;

    /**
     * 项目ID
     * 表字段: project_id
     */
    private Integer projectId;

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

    /**
     * 配置文件内容
     * 表字段: config_file_content
     */
    private String configFileContent;
}