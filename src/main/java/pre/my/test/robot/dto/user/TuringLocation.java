package pre.my.test.robot.dto.user;

/**
 * Author:qiang.zeng on 2017/4/25.
 */
public class TuringLocation {
    private String key;
    private String info;
    private String loc;
    private String lon;
    private String lat;
    private String userid;

    public TuringLocation() {
    }

    public TuringLocation(String key, String info, String loc, String lon, String lat, String userid) {
        this.key = key;
        this.info = info;
        this.loc = loc;
        this.lon = lon;
        this.lat = lat;
        this.userid = userid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
