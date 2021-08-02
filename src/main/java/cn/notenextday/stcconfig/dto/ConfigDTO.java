package cn.notenextday.stcconfig.dto;

/**
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/8/2 11:47
 */
public class ConfigDTO {

    public ConfigDTO(Integer envId, Integer projectId, Integer configId){
        this.envId = envId;
        this.projectId = projectId;
        this.configId = configId;
    }

    /**
     * 环境ID
     */
    private Integer envId;
    /**
     * 项目ID
     */
    private Integer projectId;
    /**
     * 配置ID
     */
    private Integer configId;

    public Integer getEnvId() {
        return envId;
    }

    public void setEnvId(Integer envId) {
        this.envId = envId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    @Override
    public String toString() {
        return "ConfigDTO{" +
                "envId=" + envId +
                ", projectId=" + projectId +
                ", configId=" + configId +
                '}';
    }
}
