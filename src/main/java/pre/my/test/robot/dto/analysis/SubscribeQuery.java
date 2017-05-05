package pre.my.test.robot.dto.analysis;

import pre.my.test.robot.dto.user.SubscribeAnalysis;

/**
 * Author:qiang.zeng on 2017/5/2.
 */
public class SubscribeQuery extends SubscribeAnalysis {
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
