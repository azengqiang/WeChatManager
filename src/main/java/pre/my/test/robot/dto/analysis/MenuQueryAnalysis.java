package pre.my.test.robot.dto.analysis;

/**
 * Author:qiang.zeng on 2017/5/4.
 */
public class MenuQueryAnalysis {
    /**
     * 菜单点击次数:菜单被用户点击的次数
     */
    private int clickNum;
    /**
     * 菜单点击人数:点击菜单的去重用户数;
     */
    private int userNum;
    /**
     * 人均点击次数:菜单点击次数 / 菜单点击的去重用户数
     */
    private double perCapitaNum;

    public int getClickNum() {
        return clickNum;
    }

    public void setClickNum(int clickNum) {
        this.clickNum = clickNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public double getPerCapitaNum() {
        return perCapitaNum;
    }

    public void setPerCapitaNum(double perCapitaNum) {
        this.perCapitaNum = perCapitaNum;
    }
}
