package pre.my.test.robot.dto.user;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author:qiang.zeng on 2017/2/6.
 */
@Table(name = "rb_user")
public class UserInfo {
    @Id
    @GeneratedValue()
    private Long userid;
    /**
     * 用户是否订阅该公众号标识，值为0时，
     * 代表此用户没有关注该公众号，拉取不到其余信息。
     */
    private String subscribe;

    private String openid;

    private String nickname;

    private String sex;

    private String address;

    private String lang;
    /**
     * 用户头像,最后一个数值代表正方形头像大小
     * （有0、46、64、96、132数值可选，0代表640*640正方形头像）
     * 用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */

    private String headimgurl;
    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    private String subscribe_time;
    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    private String unionid;
    /**
     * 公众号运营者对粉丝的备注
     */
    private String remark;
    /**
     * 用户所在分组
     */
    private String groupid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(String subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
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
}
