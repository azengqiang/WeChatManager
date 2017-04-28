package pre.my.test.robot.dto.material.otherList;

import pre.my.test.robot.dto.material.Materials;

/**
 * Author:qiang.zeng on 2017/3/28.
 */
public class OtherMaterials extends Materials {
    private OtherItems[] item;

    public OtherItems[] getItem() {
        return item;
    }

    public void setItem(OtherItems[] item) {
        this.item = item;
    }
}
