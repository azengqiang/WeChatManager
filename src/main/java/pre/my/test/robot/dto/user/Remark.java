package pre.my.test.robot.dto.user;

/**
 * Author:qiang.zeng on 2017/2/7.
 */
public class Remark {
    private String openid;
    private String remark;

    public Remark(String openid, String remark) {
        this.openid = openid;
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
