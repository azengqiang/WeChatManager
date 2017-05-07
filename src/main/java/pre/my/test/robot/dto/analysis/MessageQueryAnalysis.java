package pre.my.test.robot.dto.analysis;

/**
 * Author:qiang.zeng on 2017/5/5.
 */
public class MessageQueryAnalysis {
    /**
     * 消息发送人数:关注者主动发送消息的去重用户数
     */
    private int sendUserNum;
    /**
     *消息发送次数:关注者主动发送消息的次数
     */
    private int sendTimeNum;
    /**
     *人均发送次数:消息发送总次数/消息发送的去重用户数
     */
    private double perCapitaNum;

    public int getSendUserNum() {
        return sendUserNum;
    }

    public void setSendUserNum(int sendUserNum) {
        this.sendUserNum = sendUserNum;
    }

    public int getSendTimeNum() {
        return sendTimeNum;
    }

    public void setSendTimeNum(int sendTimeNum) {
        this.sendTimeNum = sendTimeNum;
    }

    public double getPerCapitaNum() {
        return perCapitaNum;
    }

    public void setPerCapitaNum(double perCapitaNum) {
        this.perCapitaNum = perCapitaNum;
    }
}
