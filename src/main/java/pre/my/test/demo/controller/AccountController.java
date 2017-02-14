package pre.my.test.demo.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pre.my.test.demo.dto.User;
import pre.my.test.demo.service.IAccountService;
import javax.servlet.http.HttpServletRequest;

/**
 * Author:qiang.zeng@hand-china.com on 2017/1/4.
 */
@Controller
@RequestMapping(value = "/demo2")
public class AccountController {
    /*  @RequestMapping("/hello")
      public ModelAndView processList() {

          return new ModelAndView("hello","str","123123");
      }*/
    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String toIndex(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = accountService.login((long) userId);
        request.setAttribute("msg", JSON.toJSONString(user));
        return "hello";
    /*    ModelAndView modelAndView = new ModelAndView();
        User user = accountService.login((long) 1);
        modelAndView.setViewName("hello");
        modelAndView.addObject("user", user);
        return modelAndView;*/
    }

}