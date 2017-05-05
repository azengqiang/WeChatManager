package pre.my.test.robot.dto.analysis;

/**
 * Author:qiang.zeng on 2017/5/2.
 */
public class SubscribeQueryAnalysis {
    private String subscribeNum;
    private String unSubscribeNum;
    private String netSubscribeNum;
    private String totalSubscribeNum;

    public String getSubscribeNum() {

        return subscribeNum;
    }

    public void setSubscribeNum(String subscribeNum) {
        this.subscribeNum = subscribeNum;
    }

    public String getUnSubscribeNum() {
        return unSubscribeNum;
    }

    public void setUnSubscribeNum(String unSubscribeNum) {
        this.unSubscribeNum = unSubscribeNum;
    }

    public String getNetSubscribeNum() {
        return netSubscribeNum;
    }

    public void setNetSubscribeNum(String netSubscribeNum) {
        this.netSubscribeNum = netSubscribeNum;
    }

    public String getTotalSubscribeNum() {
        return totalSubscribeNum;
    }

    public void setTotalSubscribeNum(String totalSubscribeNum) {
        this.totalSubscribeNum = totalSubscribeNum;
    }
}
