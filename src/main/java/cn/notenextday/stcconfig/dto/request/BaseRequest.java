package cn.notenextday.stcconfig.dto.request;

/**
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/30 10:09
 */
public class BaseRequest {
    private String messageId;
    private String data;
    private Long timeStamp;
    private String dataSource;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String toString() {
        return "BaseRequest{" +
                "messageId='" + messageId + '\'' +
                ", data='" + data + '\'' +
                ", timeStamp=" + timeStamp +
                ", dataSource='" + dataSource + '\'' +
                '}';
    }
}
