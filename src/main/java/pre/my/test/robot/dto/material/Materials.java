package pre.my.test.robot.dto.material;

/**
 * Author:qiang.zeng on 2017/3/30.
 */
public class Materials {
    /**
     * 	该类型的素材的总数
     */
    private String total_count;
    /**
     * 	本次调用获取的素材的数量
     */
    private String item_count;

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public String getItem_count() {
        return item_count;
    }

    public void setItem_count(String item_count) {
        this.item_count = item_count;
    }
}
