package pre.my.test.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/test")
public class HelloController {
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("user", "Hello world!");
		return "hello";
	}
}