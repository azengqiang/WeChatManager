package pre.my.robot.core.dto;

/**
 * 微信普通消息实体类
 * Author:qiang.zeng@hand-china.com on 2017/1/12.
 */
public class TextMessage extends Message {
    //文本消息内容
    private String Content;
    //消息id，64位整型
    private long MsgId;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }

}
