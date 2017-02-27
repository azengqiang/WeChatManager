package pre.my.test.robot.dto.message;

/**
 * 接收、回复文本消息
 * Author:qiang.zeng@hand-china.com on 2017/1/12.
 */
public class TextMessage extends Message {
    //文本消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }



}
