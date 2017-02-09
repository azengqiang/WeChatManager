package pre.my.test.robot.dto.user;

/**
 * 用户位置信息类
 * Author:qiang.zeng on 2017/2/9.
 */
public class Location {
    /**
     * 用户openid
     */
    private String FromUserName;
    /**
     * 消息创建的时间
     */
    private String CreateTime;

    /**
     *地理位置
     */
    private String Location;

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
