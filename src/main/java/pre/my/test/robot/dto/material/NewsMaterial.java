package pre.my.test.robot.dto.material;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Author:qiang.zeng on 2017/4/28.
 */
@Table(name = "rb_material_news")
public class NewsMaterial extends BaseNews {
    @Id
    @GeneratedValue()
    private Long id;
    /**
     * 永久图文素材的媒体ID
     */
    private String newsMediaId;
    /**
     * 新增素材的日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewsMediaId() {
        return newsMediaId;
    }

    public void setNewsMediaId(String newsMediaId) {
        this.newsMediaId = newsMediaId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
