package pre.my.test.manager.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pre.my.test.robot.dto.menu.Button;
import pre.my.test.robot.dto.menu.ClickButton;
import pre.my.test.robot.dto.menu.Menu;
import pre.my.test.robot.dto.menu.ViewButton;
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
    public String  toLookMenu(HttpServletRequest request) throws IOException {
        return "menu/menu_look";
    }
    @RequestMapping(value = "/menuSubmit", method = RequestMethod.POST)
    public String menuSubmit(HttpServletRequest request){
        HttpSession session = request.getSession();
        String firstMenu = request.getParameter("firstMenu");
        String menuType = request.getParameter("menuType");
        String menuName = request.getParameter("menuName");
        String menuValue = request.getParameter("menuValue");

        if(StringUtils.isEmpty(menuName)){
            request.setAttribute("hint_menuName", "菜单名称不能为空");
        }else {
            if(StringUtils.isEmpty(firstMenu)){
                setFirstMenu(request,menuType,menuName,menuValue);
            }else{
                setSecondMenu(request,firstMenu,menuType,menuName,menuValue);
            }
        }
        if(firstMenus!=null){
            Menu menu = new Menu();
            menu.setButton(firstMenus.toArray(new Button[firstMenus.size()]));
            logger.debug(JSONObject.toJSON(menu).toString());
        }
        request.setAttribute("firstMenus", firstMenus);
        return "menu/menu_create";
    }

    private void setSecondMenu(HttpServletRequest request,String firstMenu,String menuType,String menuName,String menuValue){
        if(StringUtils.isEmpty(menuValue)){
            request.setAttribute("hint_menuValue", "二级菜单键值或url路径不能为空");
        }else{
            for (int i = 0; i <firstMenus.size() ; i++) {
                Button btn = firstMenus.get(i);
                if(btn.getName().equals(firstMenu)){
                    Button[] originSubBtnArr= btn.getSub_button();
                    List<Button>  nowSubBtnArr =new ArrayList<>();
                    if(originSubBtnArr!=null){
                        if(originSubBtnArr.length>=5){
                            request.setAttribute("hint_menuName", "二级菜单最多五个");
                            break;
                        }
                        nowSubBtnArr.addAll(Arrays.asList(originSubBtnArr));
                    }
                    if(menuType.equals(Constants.MENU_TYPE_VIEW)){
                        ViewButton viewButton = new ViewButton();
                        viewButton.setName(menuName);
                        viewButton.setType(menuType);
                        viewButton.setUrl(menuValue);
                        nowSubBtnArr.add(viewButton);
                    }else if(menuType.equals(Constants.MENU_TYPE_CLICK)){
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
    private void setFirstMenu(HttpServletRequest request,String menuType,String menuName,String menuValue){
        if (firstMenus.size()>=3){
            request.setAttribute("hint_menuName", "一级菜单最多三个");
        }else{
            if(menuType.equals(Constants.MENU_TYPE_VIEW)){
                ViewButton viewButton = new ViewButton();
                viewButton.setName(menuName);
                if(StringUtils.isNotEmpty(menuValue)){
                    viewButton.setType(menuType);
                    viewButton.setUrl(menuValue);
                }
                firstMenus.add(viewButton);
            }else if(menuType.equals(Constants.MENU_TYPE_CLICK)){
                ClickButton clickButton = new ClickButton();
                clickButton.setName(menuName);
                if(StringUtils.isNotEmpty(menuValue)){
                    clickButton.setType(menuType);
                    clickButton.setKey(menuValue);
                }
                firstMenus.add(clickButton);
            }
            request.setAttribute("hint_menuValue", "如果创建一级菜单，该菜单包含子菜单，则该项不能填写；否则，必须填写该项");
        }
    }

    @RequestMapping(value = "/menuCreate", method = RequestMethod.POST)
    public String  menuCreate(HttpServletRequest request) throws IOException {
        if(firstMenus!=null){
            Menu menu = new Menu();
            menu.setButton(firstMenus.toArray(new Button[firstMenus.size()]));
            logger.debug(JSONObject.toJSON(menu).toString());
            int result = MenuUtil.createMenu(AccessTokenUtil.getValidAccessToken().getToken(), menu);
            if (result != 0) {
                request.setAttribute("hint_menuInfo", "菜单创建失败，错误码:"+result+" 菜单json数据："+JSONObject.toJSON(menu).toString());
                logger.debug("错误码:" + result);
            } else {
                logger.debug("菜单创建成功");
                request.setAttribute("hint_menuInfo", "菜单创建成功，请进入微信公众号查看");
            }
        }
        return "menu/menu_create";
    }


}
