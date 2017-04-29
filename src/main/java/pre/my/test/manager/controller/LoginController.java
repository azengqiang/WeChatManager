package pre.my.test.manager.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pre.my.test.robot.dto.user.User;
import pre.my.test.robot.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author:qiang.zeng on 2017/2/16.
 */
@Controller
@RequestMapping(value = "/admin")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserService service;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String toLogin() {
        return "template/login";
    }

    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    public void doLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) throws IOException {
        User exitUser = service.select(user);
        boolean isLogin = false;
        if (exitUser != null) {
            service.update(user);
            request.getSession().setAttribute("admin", exitUser.getUserName());
            isLogin = true;
            logger.debug("登录成功");
        } else {
            isLogin = false;
            logger.debug("登录失败");
        }
        response.setCharacterEncoding("UTF-8"); //设置编码格式
        response.setContentType("text/html");   //设置数据格式
        PrintWriter out = response.getWriter(); //获取写入对象
        out.print(JSONObject.toJSON(isLogin));
        out.flush();
    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String toIndex(HttpServletRequest request, HttpServletResponse response) {
        return "template/index";
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String toHome(HttpServletRequest request, HttpServletResponse response) {

        return "template/home";
    }

}
