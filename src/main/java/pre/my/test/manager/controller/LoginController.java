package pre.my.test.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author:qiang.zeng on 2017/2/16.
 */
@Controller
@RequestMapping(value = "/admin")
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin(){

        return "template/login";
    }
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String toIndex(HttpServletRequest request, HttpServletResponse response){

        return "template/index";
    }

}
