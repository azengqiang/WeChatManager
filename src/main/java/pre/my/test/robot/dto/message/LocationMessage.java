package pre.my.test.robot.dto.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 接收地理位置消息
 * Author:qiang.zeng on 2017/2/24.
 */
public class LocationMessage extends Message {
    /**
     * 地理位置维度
     */
    private String Location_X;
    /**
     * 地理位置经度
     */
    private String Location_Y;
    /**
     * 地图缩放大小
     */
    private String Scale;
    /**
     * 地理位置信息
     */
    private String Label;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    /**
     * 1-32（支持数字和字母组合，不能包含特殊字符）
     * 用于开发者给自己的用户分配的唯一标志（对应自己的每一个用户）
     * 图灵机器人
     */
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(String location_X) {
        Location_X = location_X;
    }

    public String getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }

    public String getScale() {
        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}
