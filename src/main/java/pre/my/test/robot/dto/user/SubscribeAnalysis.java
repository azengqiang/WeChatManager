package pre.my.test.robot.dto.user;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *
 * 新关注人数，取消关注人数，净增人数，累计关注人数
 * 谁 动作 时间
 * Author:qiang.zeng on 2017/4/25.
 */
@Table(name = "rb_subscribe")
public class SubscribeAnalysis {
    @Id
    @GeneratedValue()
    private Long id;
    private String openid;
    /**
     * 动作：取消还是关注
     */
    private String action;
    /**
     * 关注/取消日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
