package pre.my.test.manager.controller;

/**
 * Author:qiang.zeng on 2017/4/20.
 */


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pre.my.test.robot.dto.autoresponse.AutoResponseMessage;
import pre.my.test.robot.dto.user.UserInfo;
import pre.my.test.robot.service.IAutoResponseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(value = "/admin")
public class AutoResponseController {
    private static final Logger logger = LoggerFactory.getLogger(AutoResponseController.class);

    @Autowired
    private IAutoResponseService service;

    @RequestMapping(value = "/toSetSubscribeResponse", method = GET)
    public String toSetSubscribeResponse(HttpServletRequest request, HttpServletResponse response) {

        List<AutoResponseMessage> autoResponseMessages = service.select(new AutoResponseMessage(null, "关注回复语", null));
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

        List<AutoResponseMessage> autoResponseMessages = service.select(new AutoResponseMessage("关注回复", "关注回复语", null));
        if (autoResponseMessages != null && autoResponseMessages.size() != 0) {
            service.delete(autoResponseMessages.get(0));
        }
        service.save(new AutoResponseMessage("关注回复", "关注回复语", subscribeResponseMsg));

        return "redirect:toSetSubscribeResponse";
    }

    @RequestMapping(value = "/deleteSubscribeResponse", method = POST)
    public String deleteSubscribeResponse(HttpServletRequest request, HttpServletResponse response, @RequestBody AutoResponseMessage autoResponseMessage) {

        service.delete(new AutoResponseMessage(null, "关注回复语", null));
        logger.debug("成功删除关注回复语设置: " + autoResponseMessage.getResponseMsg());

        return "redirect:toSetSubscribeResponse";
    }


    @RequestMapping(value = "/addKeywordResponse", method = POST)
    public String setKeyResponse(HttpServletRequest request, HttpServletResponse response, @RequestBody AutoResponseMessage autoResponseMessage) {
        //转换用户关键字之间的所有中文逗号为英文逗号
        String keyword = autoResponseMessage.getKeywordMsg();
        keyword.replace(",", "，");
        autoResponseMessage.setKeywordMsg(keyword);
        //保存
        service.save(autoResponseMessage);
        logger.debug("add rule：" + JSON.toJSONString(autoResponseMessage));

        return "redirect:toSetKeywordResponse";
    }

    @RequestMapping(value = "/deleteKeyResponse", method = POST)
    public String deleteKeyResponse(HttpServletRequest request, HttpServletResponse response, @RequestBody List<AutoResponseMessage> autoResponseMessages) {
        for (AutoResponseMessage autoResponseMessage : autoResponseMessages) {
            service.delete(autoResponseMessage);
            logger.debug("delete rule：" + JSON.toJSONString(autoResponseMessage));
        }
        return "redirect:toSetKeywordResponse";
    }
    @RequestMapping(value = "/updateKeyResponse", method = POST)
    public String updateKeyResponse(HttpServletRequest request, HttpServletResponse response, @RequestBody AutoResponseMessage autoResponseMessage) {

            service.update(autoResponseMessage);
            logger.debug("update rule：" + JSON.toJSONString(autoResponseMessage));

        return "redirect:toSetKeywordResponse";
    }


    @RequestMapping(value = "/ruleInfo", method = RequestMethod.GET)
    @ResponseBody
    public void ruleInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //得到客户端传递的页码和每页记录数，并转换成int类型
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        List<AutoResponseMessage> autoResponseMessages = service.selectAll(pageSize, pageNumber);
        String total = String.valueOf(service.selectAll().size());
        response.setCharacterEncoding("UTF-8"); //设置编码格式
        response.setContentType("text/html");   //设置数据格式
        PrintWriter out = response.getWriter(); //获取写入对象

        String json = "{\"total\":" + total + ",\"rows\":" + JSON.toJSONString(autoResponseMessages) + "}";
        out.print(json);
        out.flush();
    }
}
