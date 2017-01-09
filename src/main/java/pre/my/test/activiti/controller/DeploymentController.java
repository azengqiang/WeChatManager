package pre.my.test.activiti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pre.my.test.demo.dto.User;
import pre.my.test.demo.service.IAccountService;

import javax.servlet.http.HttpServletRequest;

/**
 * Author:qiang.zeng@hand-china.com on 2017/1/4.
 */
@Controller
@RequestMapping(value = "/demo")
public class DeploymentController {
   /* public ModelAndView processList(){

        ModelAndView mav = new ModelAndView();
        return  mav;
    }
*/
   @Autowired
   private IAccountService accountService;

    @RequestMapping("/hello")
    public String toIndex(HttpServletRequest request,Model model){
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = accountService.login((long)userId);
        model.addAttribute("user", user);
        return "hello";
    }
}
