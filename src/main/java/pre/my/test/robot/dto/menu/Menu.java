package pre.my.test.robot.dto.menu;

/**
 * 总菜单
 * Author:qiang.zeng on 2017/1/20.
 */
public class Menu {
    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }

    private Button[] button;
}
