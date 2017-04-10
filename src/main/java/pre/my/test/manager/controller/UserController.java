package pre.my.test.manager.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pre.my.test.robot.dto.user.UserInfo;
import pre.my.test.robot.service.IUserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Author:qiang.zeng on 2017/2/15.
 */
@Controller
@RequestMapping(value = "/admin")
public class UserController {
    @Autowired
    private IUserInfoService userInfoService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    @ResponseBody
    public void userInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //得到客户端传递的页码和每页记录数，并转换成int类型
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        List<UserInfo> userInfos = userInfoService.selectAll(pageSize, pageNumber);
        int total = userInfoService.selectAll().size();
        response.setCharacterEncoding("UTF-8"); //设置编码格式
        response.setContentType("text/html");   //设置数据格式
        PrintWriter out = response.getWriter(); //获取写入对象

        String json = "{\"total\":" + total + ",\"rows\":" + JSON.toJSON(userInfos) + "}";
        out.print(json);
        out.flush();
    }

    @RequestMapping(value = "/toUserInfo", method = RequestMethod.GET)
    public String toUserInfo(HttpServletRequest request, HttpServletResponse response) {
        return "user/userinfo2";
    }

    /*@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public String toIndex(HttpServletRequest request, HttpServletResponse response) {
        List<UserInfo> userInfos = userInfoService.selectAll();
        HttpSession session = request.getSession();
        request.setAttribute("userInfos", userInfos);
        return "user/userinfo";
    }*/
}
