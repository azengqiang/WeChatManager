package pre.my.test.robot.dto.user;

/**
 * Author:qiang.zeng on 2017/2/15.
 */
public class WebPageUserInfo extends BaseUser {
    /**
     * 用户特权信息 如微信沃卡用户为（chinaunicom）
     */
    private String privilege;

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
}
