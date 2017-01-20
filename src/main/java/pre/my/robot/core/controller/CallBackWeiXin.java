package pre.my.robot.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pre.my.robot.core.util.CheckUtil;
import pre.my.robot.core.util.Constants;
import pre.my.robot.core.util.MessageUtil;
import pre.my.robot.core.util.PostMenu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Author:qiang.zeng@hand-china.com on 2017/1/12.
 */
@Controller
@RequestMapping(value = "/weixin")
public class CallBackWeiXin {
    Logger logger = LoggerFactory.getLogger(CallBackWeiXin.class);

    //接口认证
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public void checkSignature(HttpServletRequest request, HttpServletResponse response) throws IOException, NoSuchAlgorithmException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        if (signature != null) {
            if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
                logger.debug("微信接口验证成功");
                out.print(echostr);
            }
        } else {
            logger.debug("微信接口验证失败");
        }

    }

    @ResponseBody
    public void menuCreate() throws IOException {
        String ACCESS_TOKEN = "HLBzw7D-Migo3ii14oz0TAUMYK4DVLBOb3f__s5stJHILvkYuejzB7Smr80OgzNM15T6ZZ5rlnbEyV7T9mDYkBayR2Vr3t_pEPwxh2JKXf_7BbSYabRHToYATFShpf5DSMLeABAKED";
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + ACCESS_TOKEN;
        PostMenu postMenu = new PostMenu();
        // postMenu.postMenu(url,json);
    }

    //消息处理
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    private void messageHandle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //将请求的xml数据封装为map
        Map<String, String> requestMap = MessageUtil.xmlToMap(request);
        String fromUserName = requestMap.get("FromUserName");
        String toUserName = requestMap.get("ToUserName");
        String msgType = requestMap.get("MsgType");
        String content = requestMap.get("Content");
        //String createTime = requestMap.get("CreateTime");

        PrintWriter out = response.getWriter();
        String respMessage = "异常消息！";
        //根据消息类型，进行处理
        switch (msgType) {
            case Constants.MSG_TYPE_TEXT:
                respMessage = textMessageHandle(fromUserName, toUserName, content);
                break;
            case Constants.MSG_TYPE_EVENT:
                String eventType = requestMap.get("Event");// 事件类型
                String eventKey = requestMap.get("EventKey");//自定义事件
                respMessage = eventMessageHandle(fromUserName, toUserName, content, eventType, eventKey);
                break;
            default:
                break;
        }
        //menuCreate();
        //返回数据
        out.print(respMessage);
    }

    //文本消息处理
    private String textMessageHandle(String fromUserName, String toUserName, String content) {
        if (content.equals("1")) {

        } else if (content.equals("2")) {
            return MessageUtil.initTextMessage(fromUserName, toUserName, MessageUtil.firstMenu());
        } else if (content.equals("?") || content.equals("？")) {

        }
        return MessageUtil.initTextMessage(fromUserName, toUserName, "您发送的内容是：" + content);
    }

    //事件处理
    private String eventMessageHandle(String fromUserName, String toUserName, String content, String eventType, String eventKey) {
        String respEventMessage = "";
        if (eventType.equals(Constants.EVENT_TYPE_SUBSCRIBE)) {// 订阅
            respEventMessage = MessageUtil.initTextMessage(fromUserName, toUserName, MessageUtil.firstMenu());
        } else if (eventType.equals(Constants.EVENT_TYPE_CLICK)) {// 自定义菜单点击事件

        } else if (eventType.equals(Constants.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅

        }
        return respEventMessage;
    }


}
