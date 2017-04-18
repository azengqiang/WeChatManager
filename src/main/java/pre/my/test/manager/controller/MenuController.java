package pre.my.test.manager.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pre.my.test.robot.dto.menu.*;
import pre.my.test.robot.util.AccessTokenUtil;
import pre.my.test.robot.util.Constants;
import pre.my.test.robot.util.MenuUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author:qiang.zeng on 2017/2/16.
 */
@Controller
@RequestMapping(value = "/admin")
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    List<Button> firstMenus = new ArrayList<>();

    @RequestMapping(value = "/toCreateMenu", method = RequestMethod.GET)
    public String toCreateMenu(HttpServletRequest request) throws UnsupportedEncodingException {
        firstMenus.clear();
        request.setAttribute("hint_menuValue", "如果一级菜单包含子菜单，该项不能填写；否则，必须填写该项");
        return "menu/menu_create";
    }

    @RequestMapping(value = "/toLookMenu", method = RequestMethod.GET)
    public String toLookMenu(HttpServletRequest request) throws IOException {
        JSONObject jsonObject = MenuUtil.queryMenu(AccessTokenUtil.getValidAccessToken().getToken());
        JSONArray menu = jsonObject.getJSONObject("menu").getJSONArray("button");
        List<MenuDetail> menuDetails = new ArrayList<>();
        getMenuDetails(menu, menuDetails);
        request.setAttribute("menuDetails", menuDetails);
        return "menu/menu_look";
    }

    private void getMenuDetails(JSONArray array, List<MenuDetail> menuDetails) {
        for (int btnIndex = 0; btnIndex < array.size(); btnIndex++) {
            JSONObject btn = (JSONObject) array.get(btnIndex);
            menuDetails.add(setMenuDetail(btn, null));
            //获取二级菜单
            String name = btn.getString("name");
            JSONArray subButtons = btn.getJSONArray("sub_button");
            if (subButtons != null && subButtons.size() != 0) {
                for (int subBtnIndex = 0; subBtnIndex < subButtons.size(); subBtnIndex++) {
                    JSONObject subBtn = (JSONObject) subButtons.get(subBtnIndex);
                    menuDetails.add(setMenuDetail(subBtn, name));
                }
            }
        }
    }

    private MenuDetail setMenuDetail(JSONObject jsonObject, String supName) {
        MenuDetail menuDetail = new MenuDetail();
        menuDetail.setType(jsonObject.getString("type"));
        menuDetail.setName(jsonObject.getString("name"));
        String key = jsonObject.getString("key");
        String url = jsonObject.getString("url");
        String mediaId = jsonObject.getString("media_id");
        if (key != null) {
            menuDetail.setValue(key);
        } else if (url != null) {
            menuDetail.setValue(url);
        } else if (mediaId != null) {
            menuDetail.setValue(mediaId);
        }
        menuDetail.setSuperiorName(supName);
        return menuDetail;
    }

    @RequestMapping(value = "/menuSubmit", method = RequestMethod.POST)
    public String menuSubmit(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String firstMenu = request.getParameter("firstMenu");
        String menuType = request.getParameter("menuType");
        String menuName = request.getParameter("menuName");
        String menuValue = request.getParameter("menuValue");

        if (StringUtils.isEmpty(menuName)) {
            request.setAttribute("hint_menuName", "菜单名称不能为空");
        } else {
            if (StringUtils.isEmpty(firstMenu)) {
                setFirstMenu(request, menuType, menuName, menuValue);
            } else {
                setSecondMenu(request, firstMenu, menuType, menuName, menuValue);
            }
        }
        if (firstMenus != null) {
            Menu menu = new Menu();
            menu.setButton(firstMenus.toArray(new Button[firstMenus.size()]));
            logger.debug(JSONObject.toJSON(menu).toString());
        }
        request.setAttribute("firstMenus", firstMenus);
        return "menu/menu_create";
    }

    private void setSecondMenu(HttpServletRequest request, String firstMenu, String menuType, String menuName, String menuValue) {
        if (StringUtils.isEmpty(menuValue)) {
            request.setAttribute("hint_menuValue", "二级菜单键值或url路径不能为空");
        } else {
            for (int i = 0; i < firstMenus.size(); i++) {
                Button btn = firstMenus.get(i);
                if (btn.getName().equals(firstMenu)) {
                    Button[] originSubBtnArr = btn.getSub_button();
                    List<Button> nowSubBtnArr = new ArrayList<>();
                    if (originSubBtnArr != null) {
                        if (originSubBtnArr.length >= 5) {
                            request.setAttribute("hint_menuName", "二级菜单最多五个");
                            break;
                        }
                        nowSubBtnArr.addAll(Arrays.asList(originSubBtnArr));
                    }
                    if (Constants.MENU_TYPE_VIEW.equals(menuType)) {
                        ViewButton viewButton = new ViewButton();
                        viewButton.setName(menuName);
                        viewButton.setType(menuType);
                        viewButton.setUrl(menuValue);
                        nowSubBtnArr.add(viewButton);
                    } else if (Constants.MENU_TYPE_CLICK.equals(menuType)) {
                        ClickButton clickButton = new ClickButton();
                        clickButton.setName(menuName);
                        clickButton.setType(menuType);
                        clickButton.setKey(menuValue);
                        nowSubBtnArr.add(clickButton);
                    }
                    btn.setSub_button(nowSubBtnArr.toArray(new Button[nowSubBtnArr.size()]));
                }
            }
        }
    }

    private void setFirstMenu(HttpServletRequest request, String menuType, String menuName, String menuValue) {
        if (firstMenus.size() >= 3) {
            request.setAttribute("hint_menuName", "一级菜单最多三个");
        } else {
            if (Constants.MENU_TYPE_VIEW.equals(menuType)) {
                ViewButton viewButton = new ViewButton();
                viewButton.setName(menuName);
                if (StringUtils.isNotEmpty(menuValue)) {
                    viewButton.setType(menuType);
                    viewButton.setUrl(menuValue);
                }
                firstMenus.add(viewButton);
            } else if (Constants.MENU_TYPE_CLICK.equals(menuType)) {
                ClickButton clickButton = new ClickButton();
                clickButton.setName(menuName);
                if (StringUtils.isNotEmpty(menuValue)) {
                    clickButton.setType(menuType);
                    clickButton.setKey(menuValue);
                }
                firstMenus.add(clickButton);
            }
            request.setAttribute("hint_menuValue", "如果创建一级菜单，该菜单包含子菜单，则该项不能填写；否则，必须填写该项");
        }
    }

    @RequestMapping(value = "/menuCreate", method = RequestMethod.POST)
    public String menuCreate(HttpServletRequest request) throws IOException {
        if (firstMenus != null) {
            Menu menu = new Menu();
            menu.setButton(firstMenus.toArray(new Button[firstMenus.size()]));
            logger.debug(JSONObject.toJSON(menu).toString());
            int result = MenuUtil.createMenu(AccessTokenUtil.getValidAccessToken().getToken(), menu);
            if (result != 0) {
                request.setAttribute("hint_menuInfo", "菜单创建失败，错误码:" + result + " 菜单json数据：" + JSONObject.toJSON(menu).toString());
                logger.debug("错误码:" + result);
            } else {
                logger.debug("菜单创建成功");
                request.setAttribute("hint_menuInfo", "菜单创建成功，请进入微信公众号查看");
            }
        }
        return "menu/menu_create";
    }


}
