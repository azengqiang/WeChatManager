package pre.my.test.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pre.my.test.robot.dto.user.UserInfo;
import pre.my.test.robot.service.IUserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Author:qiang.zeng on 2017/2/15.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private IUserInfoService userInfoService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public String toIndex(HttpServletRequest request, HttpServletResponse response){
        List<UserInfo> userInfos = userInfoService.selectAll();
        HttpSession session= request.getSession();
        request.setAttribute("userInfos", userInfos);
        return "user/userinfo";
    }
}
