package pre.my.test.robot.dto.material.detail;

/**
 * Author:qiang.zeng on 2017/3/28.
 */
public class NewsItems {
    private String meida_id;
    private NewsContent content;
    /**
     * 这篇图文消息素材的最后更新时间
     */
    private String update_time;

    public String getMeida_id() {
        return meida_id;
    }

    public void setMeida_id(String meida_id) {
        this.meida_id = meida_id;
    }

    public NewsContent getContent() {
        return content;
    }

    public void setContent(NewsContent content) {
        this.content = content;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
