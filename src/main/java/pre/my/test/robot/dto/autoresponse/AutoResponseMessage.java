package pre.my.test.robot.dto.autoresponse;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 自动回复实体类
 * Author:qiang.zeng on 2017/4/21.
 */
public class AutoResponseMessage {
    @Id
    @GeneratedValue()
    private Long autoResponseMsgId;
    /**
     * 关键词
     */
    private String keywordMsg;
    private String responseMsg;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    public AutoResponseMessage(){}

    public AutoResponseMessage(String keywordMsg, String responseMsg) {
        this.keywordMsg = keywordMsg;
        this.responseMsg = responseMsg;
    }

    public Long getAutoResponseMsgId() {
        return autoResponseMsgId;
    }

    public void setAutoResponseMsgId(Long autoResponseMsgId) {
        this.autoResponseMsgId = autoResponseMsgId;
    }

    public String getKeywordMsg() {
        return keywordMsg;
    }

    public void setKeywordMsg(String keywordMsg) {
        this.keywordMsg = keywordMsg;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
