package pre.my.test.robot.dto.user;

import javax.persistence.Table;

/**
 * Author:qiang.zeng on 2017/2/6.
 */
@Table(name = "rb_userinfo")
public class UserInfo extends BaseUser{

    /**
     * 用户是否订阅该公众号标识，值为0时，
     * 代表此用户没有关注该公众号，拉取不到其余信息。
     */
    private String subscribe;

    private String lang;
    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    private String subscribe_time;
    /**
     * 公众号运营者对粉丝的备注
     */
    private String remark;
    /**
     * 用户所在分组Id
     */
    private String groupid;
    /**
     * 用户所在分组名称
     */
    private String groupName;

    public UserInfo(){}

    public UserInfo(String groupid, String remark,String openId) {
        super.setOpenid(openId);
        this.groupid = groupid;
        this.remark = remark;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(String subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
