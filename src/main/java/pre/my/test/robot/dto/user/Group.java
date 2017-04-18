package pre.my.test.robot.dto.user;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Author:qiang.zeng on 2017/2/8.
 */
@Table(name = "rb_group")
public class Group {
    @Id
    @GeneratedValue()
    private Long groupId;
    private String id;
    private String name;
    private String count;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    public Group() {
    }

    public Group(String id, String name, String count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
