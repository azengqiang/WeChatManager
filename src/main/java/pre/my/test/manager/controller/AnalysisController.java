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
public class AnalysisController {
    @RequestMapping(value = "toGraphicAnalysis", method = RequestMethod.GET)
    public String toGraphicAnalysis() {

        return "analysis/graphic";
    }

    @RequestMapping(value = "toMenuAnalysis", method = RequestMethod.GET)
    public String toMenuAnalysis(HttpServletRequest request, HttpServletResponse response) {

        return "analysis/menu";
    }

    @RequestMapping(value = "toMessageAnalysis", method = RequestMethod.GET)
    public String toMessageAnalysis(HttpServletRequest request, HttpServletResponse response) {

        return "analysis/message";
    }

    @RequestMapping(value = "toUserAnalysis", method = RequestMethod.GET)
    public String toUserAnalysis(HttpServletRequest request, HttpServletResponse response) {

        return "analysis/user";
    }

}
