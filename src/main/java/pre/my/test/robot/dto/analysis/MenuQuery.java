package pre.my.test.robot.dto.analysis;

import pre.my.test.robot.dto.menu.MenuAnalysis;

/**
 * Author:qiang.zeng on 2017/5/4.
 */
public class MenuQuery extends MenuAnalysis {
    private String startDate;

    private String endDate;


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
