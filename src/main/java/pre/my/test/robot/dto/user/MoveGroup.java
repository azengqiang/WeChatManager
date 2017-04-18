package pre.my.test.robot.dto.user;

/**
 * Author:qiang.zeng on 2017/4/17.
 */
public class MoveGroup {
    /**
     * 用户唯一标识符openid的列表（size不能超过50）
     */
    private String[] openid_list;
    /**
     * 用户唯一标识符
     */
    private String openid;
    /**
     * 分组Id
     */
    private String to_groupid;


    public String[] getOpenid_list() {
        return openid_list;
    }

    public void setOpenid_list(String[] openid_list) {
        this.openid_list = openid_list;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTo_groupid() {
        return to_groupid;
    }

    public void setTo_groupid(String to_groupid) {
        this.to_groupid = to_groupid;
    }
}
