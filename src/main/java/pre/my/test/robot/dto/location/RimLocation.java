package pre.my.test.robot.dto.location;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Author:qiang.zeng on 2017/4/25.
 */
@Table(name = "rb_location")
public class RimLocation {
    @Id
    @GeneratedValue()
    private Long id;
    //什么人
    private String openid;
    //在什么地方
    private String address;
    //干什么
    private String rimType;
    //回调
    private String callBack;
    //时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRimType() {
        return rimType;
    }

    public void setRimType(String rimType) {
        this.rimType = rimType;
    }

    public String getCallBack() {
        return callBack;
    }

    public void setCallBack(String callBack) {
        this.callBack = callBack;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
