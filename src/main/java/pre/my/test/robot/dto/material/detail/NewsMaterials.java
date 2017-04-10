package pre.my.test.robot.dto.material.detail;

/**
 * Author:qiang.zeng on 2017/3/28.
 */
public class NewsMaterials extends  Materials {

    private NewsItems[] item;

    public NewsItems[] getItem() {
        return item;
    }

    public void setItem(NewsItems[] item) {
        this.item = item;
    }
}
