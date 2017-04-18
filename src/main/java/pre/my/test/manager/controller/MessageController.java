package pre.my.test.manager.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pre.my.test.robot.dto.user.MsgBack;
import pre.my.test.robot.service.IMsgBackService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Author:qiang.zeng on 2017/2/15.
 */
@Controller
@RequestMapping(value = "/admin")
public class MessageController {
    @Autowired
    private IMsgBackService msgBackService;

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @RequestMapping(value = "/toTextMessage", method = RequestMethod.GET)
    public String toTextMessage(HttpServletRequest request, HttpServletResponse response) {
        return "message/message_text";
    }

    @RequestMapping(value = "/toGraphicMessage", method = RequestMethod.GET)
    public String toGraphicMessage(HttpServletRequest request, HttpServletResponse response) {
        return "message/message_graphic";
    }

    @RequestMapping(value = "/lookMessage", method = RequestMethod.GET)
    @ResponseBody
    public void lookMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //得到客户端传递的页码和每页记录数，并转换成int类型
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        List<MsgBack> msgBacks = msgBackService.selectAllMsgBack(pageSize, pageNumber);
        int total = msgBackService.selectCount();
        response.setCharacterEncoding("UTF-8"); //设置编码格式
        response.setContentType("text/html");   //设置数据格式
        PrintWriter out = response.getWriter(); //获取写入对象

        String json = "{\"total\":" + total + ",\"rows\":" + JSON.toJSONString(msgBacks) + "}";
        out.print(json);
        out.flush();
    }


}
