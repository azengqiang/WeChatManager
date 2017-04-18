package pre.my.test.robot.dto.menu;

import java.io.Serializable;

/**
 * Author:qiang.zeng on 2017/4/10.
 */
public class MenuDetail implements Serializable {
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单属性值
     */
    private String value;
    /**
     * 菜单类型
     */
    private String type;
    /**
     * 上级菜单名称
     */
    private String superiorName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSuperiorName() {
        return superiorName;
    }

    public void setSuperiorName(String superiorName) {
        this.superiorName = superiorName;
    }
}
