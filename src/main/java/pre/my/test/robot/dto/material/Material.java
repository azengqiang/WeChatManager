package pre.my.test.robot.dto.material;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Author:qiang.zeng on 2017/3/27.
 */
@Table(name = "rb_material")
public class Material {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue()
    private Long materialId;
    /**
     * 素材id
     */
    private String mediaId;
    private String name;
    /**
     * 文件本地路径
     */
    private String path;
    /**
     * 文件媒体类型
     */
    private String mediaType;

    private String url;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
