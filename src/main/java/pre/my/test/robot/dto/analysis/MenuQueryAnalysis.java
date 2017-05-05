package pre.my.test.robot.dto.analysis;

/**
 * Author:qiang.zeng on 2017/5/4.
 */
public class MenuQueryAnalysis {
    /**
     * 菜单点击次数:菜单被用户点击的次数
     */
    private String clickNum;
    /**
     * 菜单点击人数:点击菜单的去重用户数;
     */
    private String userNum;
    /**
     * 人均点击次数:菜单点击次数 / 菜单点击的去重用户数
     */
    private String perCapitaNum;

    public String getClickNum() {
        return clickNum;
    }

    public void setClickNum(String clickNum) {
        this.clickNum = clickNum;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getPerCapitaNum() {
        return perCapitaNum;
    }

    public void setPerCapitaNum(String perCapitaNum) {
        this.perCapitaNum = perCapitaNum;
    }
}
