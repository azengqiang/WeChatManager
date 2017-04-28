package pre.my.test.robot.dto.material;

/**
 * Author:qiang.zeng on 2017/3/28.
 */
public class MaterialBatchGetParam {
    /**
     * 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     */
    private String type;
    /**
     * 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     */
    private String offset;
    /**
     * 返回素材的数量，取值在1到20之间
     */
    private String count;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
