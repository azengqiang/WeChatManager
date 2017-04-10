package pre.my.test.robot.util;

import com.alibaba.fastjson.JSONObject;
import pre.my.test.robot.dto.menu.Button;
import pre.my.test.robot.dto.menu.ClickButton;
import pre.my.test.robot.dto.menu.Menu;
import pre.my.test.robot.dto.menu.ViewButton;

import java.io.IOException;

/**
 * 菜单工具类
 * Author:qiang.zeng on 2017/2/6.
 */
public class MenuUtil {
    /**
     * 组装菜单
     * @return Menu对象
     */
    public static Menu initMenu() {
        Menu menu = new Menu();
        ClickButton button11 = new ClickButton();
        button11.setName("911成员介绍");
        button11.setType(Constants.MENU_TYPE_CLICK);
        button11.setKey("11");
        ClickButton button12 = new ClickButton();
        button12.setName("智能陪聊");
        button12.setType(Constants.MENU_TYPE_CLICK);
        button12.setKey("12");
        Button button1 = new Button();
        button1.setName("菜单");
        button1.setSub_button(new Button[]{button11,button12});

        ViewButton button21 = new ViewButton();
        button21.setName("百度一下");
        button21.setType(Constants.MENU_TYPE_VIEW);
        button21.setUrl("http://www.baidu.com");
        Button button2 = new Button();
        button2.setName("游戏");
        button2.setSub_button(new Button[]{button21});

        ClickButton button31 = new ClickButton();
        button31.setName("二维码扫描");
        button31.setType(Constants.MENU_TYPE_SCAN_CODE_PUSH);
        button31.setKey("31");
        ClickButton button32 = new ClickButton();
        button32.setName("地理位置");
        button32.setType(Constants.MENU_TYPE_LOCATION_SELECT);
        button32.setKey("32");
        Button button3 = new Button();
        button3.setName("关于我");
        button3.setSub_button(new Button[]{button31, button32});

        menu.setButton(new Button[]{button1, button2, button3});
        return menu;
    }

    /**
     * 创建菜单
     *
     * @param token
     * @param menu  Menu对象
     * @return  返回码errcode 成功；0 失败：其他
     * @throws IOException
     */
    public static int createMenu(String token, Menu menu) throws IOException {
        int result = 0;
        String url = Constants.MENU_CREATE_URL.replace("ACCESS_TOKEN", token);
        //post请求创建菜单
        JSONObject jsonObject = HttpConnectUtil.doPostStr(url, JSONObject.toJSON(menu).toString());
        if (jsonObject != null) {
            result = jsonObject.getInteger("errcode");
        }
        return result;
    }

    /**
     * 查询菜单
     * @param token
     * @return 菜单json数据
     * @throws IOException
     */
    public static  JSONObject queryMenu(String token) throws IOException {
        String url = Constants.MENU_QUERY_URL.replace("ACCESS_TOKEN", token);
        return HttpConnectUtil.doGetStr(url);
    }

    /**
     * 删除菜单
     * @param token
     * @return 返回码errcode 成功：0 失败：其他
     * @throws IOException
     */
    public static  int deleteMenu(String token) throws IOException {
        int result = 0;
        String url = Constants.MENU_DELETE_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = HttpConnectUtil.doGetStr(url);
        if (jsonObject != null) {
            result = jsonObject.getInteger("errcode");
        }
        return result;
    }
}
