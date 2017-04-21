package pre.my.test.manager.controller;

/**
 * Author:qiang.zeng on 2017/4/20.
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pre.my.test.robot.dto.autoresponse.AutoResponseMessage;
import pre.my.test.robot.service.AutoResponseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(value = "/admin")
public class AutoResponseController {
    private static final Logger logger = LoggerFactory.getLogger(AutoResponseController.class);

    @Autowired
    private AutoResponseService service;

    @RequestMapping(value = "/toSetSubscribeResponse", method = GET)
    public String toSetSubscribeResponse(HttpServletRequest request, HttpServletResponse response) {

        List<AutoResponseMessage> autoResponseMessages = service.select(new AutoResponseMessage("关注回复语", null));
        if (autoResponseMessages != null && autoResponseMessages.size() != 0) {
            logger.debug("关注回复语: " + autoResponseMessages.get(0).getResponseMsg());
            request.setAttribute("subscribeResponseMsg", autoResponseMessages.get(0));
        } else {
            request.setAttribute("subscribeResponseMsg", null);
            logger.debug("关注回复语: " + "暂时无关注回复语设置");
        }
        return "autoresponse/subscribe_response";
    }

    @RequestMapping(value = "/toSetKeywordResponse", method = GET)
    public String toSetKeyResponse(HttpServletRequest request, HttpServletResponse response) {
        return "autoresponse/keyword_response";
    }

    @RequestMapping(value = "/setSubscribeResponse", method = POST)
    public String setSubscribeResponse(HttpServletRequest request, HttpServletResponse response) {
        //欢迎关注swpu911公众号
        String subscribeResponseMsg = (String) request.getParameter("subscribeResponseMsg");
        logger.debug("关注回复语设置: " + subscribeResponseMsg);

        List<AutoResponseMessage> autoResponseMessages = service.select(new AutoResponseMessage("关注回复语", null));
        if (autoResponseMessages != null && autoResponseMessages.size() != 0) {
            service.delete(autoResponseMessages.get(0));
        }
        service.save(new AutoResponseMessage("关注回复语", subscribeResponseMsg));

        return "redirect:toSetSubscribeResponse";
    }

    @RequestMapping(value = "/deleteSubscribeResponse", method = POST)
    public String deleteSubscribeResponse(HttpServletRequest request, HttpServletResponse response,@RequestBody AutoResponseMessage autoResponseMessage){

        service.delete(new AutoResponseMessage("关注回复语", null));
        logger.debug("成功删除关注回复语设置: " + autoResponseMessage.getResponseMsg());

        return "redirect:toSetSubscribeResponse";
    }


    @RequestMapping(value = "/setKeywordResponse", method = POST)
    public String setKeyResponse(HttpServletRequest request, HttpServletResponse response) {
        return "autoresponse/keyword_response";
    }
}
