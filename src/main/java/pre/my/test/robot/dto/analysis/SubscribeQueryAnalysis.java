package pre.my.test.robot.dto.analysis;

/**
 * Author:qiang.zeng on 2017/5/2.
 */
public class SubscribeQueryAnalysis {
    private int subscribeNum;
    private int unSubscribeNum;
    private int netSubscribeNum;
    private int totalSubscribeNum;

    public int getSubscribeNum() {
        return subscribeNum;
    }

    public void setSubscribeNum(int subscribeNum) {
        this.subscribeNum = subscribeNum;
    }

    public int getUnSubscribeNum() {
        return unSubscribeNum;
    }

    public void setUnSubscribeNum(int unSubscribeNum) {
        this.unSubscribeNum = unSubscribeNum;
    }

    public int getNetSubscribeNum() {
        return netSubscribeNum;
    }

    public void setNetSubscribeNum(int netSubscribeNum) {
        this.netSubscribeNum = netSubscribeNum;
    }

    public int getTotalSubscribeNum() {
        return totalSubscribeNum;
    }

    public void setTotalSubscribeNum(int totalSubscribeNum) {
        this.totalSubscribeNum = totalSubscribeNum;
    }
}
