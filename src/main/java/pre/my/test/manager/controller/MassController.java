package pre.my.test.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author:qiang.zeng on 2017/4/27.
 */
@Controller
@RequestMapping(value = "/admin")
public class MassController {
    @RequestMapping(value = "/toMassMessage", method = RequestMethod.GET)
    public String toMassMessage(HttpServletRequest request, HttpServletResponse response) {
        return "message/mass";
    }
}
