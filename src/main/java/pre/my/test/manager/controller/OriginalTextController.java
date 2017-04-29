package pre.my.test.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Author:qiang.zeng on 2017/4/28.
 */
@Controller
@RequestMapping(value = "/original")
public class OriginalTextController {
    @RequestMapping(value = "/text", method = RequestMethod.GET)
    public String toIndex(HttpServletRequest request) {
        String name = request.getParameter("name");
        return "original/"+name;
    /*    ModelAndView modelAndView = new ModelAndView();
        User user = accountService.login((long) 1);
        modelAndView.setViewName("hello");
        modelAndView.addObject("user", user);
        return modelAndView;*/
    }
}
