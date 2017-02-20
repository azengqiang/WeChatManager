package pre.my.test.manager.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pre.my.test.robot.dto.menu.Button;
import pre.my.test.robot.dto.menu.Menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    int index=0;
    @RequestMapping(value = "/createMenu", method = RequestMethod.GET)
    public String toCreateMenu(){
        firstMenus.clear();
        Button btn = new Button();
        btn.setName("");
        firstMenus.add(btn);
        return "menu/menu_create";
    }
    @RequestMapping(value = "/menuSubmit", method = RequestMethod.GET)
    public String menuSubmit(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String firstMenu = request.getParameter("firstMenu");
        String menuType = request.getParameter("menuType");
        String menuName = request.getParameter("menuName");
        if(firstMenu==null || firstMenu.equals("")){
            if(firstMenus.size()>=4){
                request.setAttribute("hint_menuName", "一级菜单最多三个");
                logger.debug("一级菜单最多三个，请重新设置");
            }else{
                if(menuName==null || menuName.equals("")){
                    request.setAttribute("hint_menuName", "菜单名称不能为空");
                }else{
                    Button btn = new Button();
                    btn.setName(menuName);
                    btn.setType(menuType);
                    firstMenus.add(btn);
                }
            }

        }else{
            if(menuName==null || menuName.equals("")){
                request.setAttribute("hint_menuName", "菜单名称不能为空");
            }else{
                for (int i = 0; i <firstMenus.size() ; i++) {
                    Button btn = firstMenus.get(i);
                    if(btn.getName().equals(firstMenu)){
                        Button[] originSubBtn= btn.getSub_button();
                        List<Button>  subBtnList =new ArrayList<>();
                        if(originSubBtn!=null){
                            if(originSubBtn.length>=5){
                                request.setAttribute("hint_menuName", "二级菜单最多五个");
                                break;
                            }
                            subBtnList.addAll(Arrays.asList(originSubBtn));
                        }
                        Button subBtn = new Button();
                        subBtn.setName(menuName);
                        subBtn.setType(menuType);
                        subBtnList.add(subBtn);
                        btn.setSub_button(subBtnList.toArray(new Button[subBtnList.size()]));
                    }
                }
            }
        }
       // firstMenus.remove(0);
        Menu menu = new Menu();
        menu.setButton((Button[])firstMenus.toArray(new Button[firstMenus.size()]));
        logger.debug(JSONObject.toJSON(menu).toString());
        request.setAttribute("firstMenus", firstMenus);
        return "menu/menu_create";
    }
    @RequestMapping(value = "/menuCreate", method = RequestMethod.GET)
    public String  menuCreate(){
        return null;
    }
}
