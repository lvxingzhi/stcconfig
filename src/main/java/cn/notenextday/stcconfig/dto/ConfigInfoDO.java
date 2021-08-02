package cn.notenextday.stcconfig.dto;

import java.util.Date;

/**
 * 配置表
 */
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
    private String configFileVersion;

    /**
     * 配置文件上一版本号
     * 表字段: config_file_version_previous
     */
    private String configFileVersionPrevious;

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
    private Date createTime;

    /**
     * 修改时间
     * 表字段: update_time
     */
    private Date updateTime;

    /**
     * 配置文件内容
     * 表字段: config_file_content
     */
    private String configFileContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getConfigFileName() {
        return configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    public String getConfigFileType() {
        return configFileType;
    }

    public void setConfigFileType(String configFileType) {
        this.configFileType = configFileType;
    }

    public String getConfigFileVersion() {
        return configFileVersion;
    }

    public void setConfigFileVersion(String configFileVersion) {
        this.configFileVersion = configFileVersion;
    }

    public String getConfigFileVersionPrevious() {
        return configFileVersionPrevious;
    }

    public void setConfigFileVersionPrevious(String configFileVersionPrevious) {
        this.configFileVersionPrevious = configFileVersionPrevious;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getConfigIndex() {
        return configIndex;
    }

    public void setConfigIndex(Integer configIndex) {
        this.configIndex = configIndex;
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

    public String getConfigFileContent() {
        return configFileContent;
    }

    public void setConfigFileContent(String configFileContent) {
        this.configFileContent = configFileContent;
    }
}