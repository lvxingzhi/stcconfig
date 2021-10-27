package cn.notenextday.stcconfigclient.container;

/**
 * 属性节点
 *
 * @Author xingzhi.lv
 * @Version 2.1
 * @Date 2021/10/27 11:10
 */
public class FieldNode {
    /**
     * 属性名称
     */
    private String fieldName;
    /**
     * 注解配置名称
     */
    private String annokeyName;
    /**
     * 配置文件名称(可选)
     */
    private String annoFileName;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getAnnokeyName() {
        return annokeyName;
    }

    public void setAnnokeyName(String annokeyName) {
        this.annokeyName = annokeyName;
    }

    public String getAnnoFileName() {
        return annoFileName;
    }

    public void setAnnoFileName(String annoFileName) {
        this.annoFileName = annoFileName;
    }
}
