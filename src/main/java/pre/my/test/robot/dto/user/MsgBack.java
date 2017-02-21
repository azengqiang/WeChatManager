package pre.my.test.robot.dto.user;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Author:qiang.zeng on 2017/2/10.
 */
@Table(name = "rb_message")
public class MsgBack {

    @Id
    @GeneratedValue()
    private Long msgid;
    /**
     * 用户外键，方便查询用户信息
     */
    private Long userid;
    @Transient
    private String nickname;
    /**
     * 用户输入的内容
     */
    private String userContent;
    /**
     * 机器人回复的内容
     */
    private String robotContent;
    /**
     * 创建时间
     */
    private String creationDate;

    public Long getMsgid() {
        return msgid;
    }

    public void setMsgid(Long msgid) {
        this.msgid = msgid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUserContent() {
        return userContent;
    }

    public void setUserContent(String userContent) {
        this.userContent = userContent;
    }

    public String getRobotContent() {
        return robotContent;
    }

    public void setRobotContent(String robotContent) {
        this.robotContent = robotContent;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
