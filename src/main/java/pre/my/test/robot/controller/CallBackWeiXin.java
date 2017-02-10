package pre.my.test.robot.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pre.my.test.robot.dto.AccessToken;
import pre.my.test.robot.dto.user.Group;
import pre.my.test.robot.dto.user.Location;
import pre.my.test.robot.dto.user.MsgBack;
import pre.my.test.robot.dto.user.UserInfo;
import pre.my.test.robot.service.IMsgBackService;
import pre.my.test.robot.service.IUserInfoService;
import pre.my.test.robot.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author:qiang.zeng@hand-china.com on 2017/1/12.
 */
@Controller
@RequestMapping(value = "/robot")
public class CallBackWeiXin {
    private static final Logger logger = LoggerFactory.getLogger(CallBackWeiXin.class);
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IMsgBackService msgBackService;

    //接口认证
    @RequestMapping(method = RequestMethod.GET)
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


    //消息处理
    @RequestMapping(method = RequestMethod.POST)
    private void messageHandle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //将请求的xml数据封装为map
        Map<String, String> requestMap = MessageUtil.xmlToMap(request);
        String fromUserName = requestMap.get("FromUserName");
        String toUserName = requestMap.get("ToUserName");
        String msgType = requestMap.get("MsgType");
        String content = requestMap.get("Content");
        String createTime = requestMap.get("CreateTime");
        //String createTime = requestMap.get("CreateTime");

        PrintWriter out = response.getWriter();
        String respMessage = " ";
        //接收消息，进行处理
        switch (msgType) {
            case Constants.MSG_TYPE_TEXT:
                //文本消息处理
                respMessage = textMessageHandle(fromUserName, toUserName, content);
                break;
            case Constants.MSG_TYPE_EVENT:
                //事件处理
                respMessage = eventMessageHandle(fromUserName, toUserName, content, createTime, requestMap);
                break;
            case Constants.MSG_TYPE_LOCATION:
                //地理位置处理
                String label = requestMap.get("Label");
                respMessage = MessageUtil.initTextMessage(toUserName, fromUserName, label);
                break;
            case Constants.MSG_TYPE_IMAGE:
                break;
            case Constants.MSG_TYPE_VOICE:
                break;
            case Constants.MSG_TYPE_VIDEO:
                break;
            case Constants.MSG_TYPE_SHORT_VIDEO:
                break;
            case Constants.MSG_TYPE_LINK:
                break;
            default:
                break;
        }
        //返回数据
        out.print(respMessage);
    }

    //文本消息处理
    public String textMessageHandle(String fromUserName, String toUserName, String content) throws IOException {
        if (content.equals("1")) {
            /*AccessToken token = AccessTokenUtil.getValidAccessToken();
            List<String> list= UserManagerUtil.getUserInfoList(token.getToken());
            for(int i=0;i<list.size();i++){
                UserInfo userInfo = UserManagerUtil.getUserInfo(token.getToken(), list.get(i));
                userInfoService.save(userInfo);
            }*/
        } else if (content.equals("2")) {
            return MessageUtil.initNewsMessage(fromUserName, toUserName);
           /* return MessageUtil.initTextMessage(fromUserName, toUserName, MessageUtil.firstMenu());*/
        } else if (content.equals("3")) {
         /*
            Map<String,String> map = new HashMap<>();
            map.put("o9eW3w5m36mEn4uH2QLvJQjZ-nxI","前端全栈wuli浪");
            map.put("o9eW3w8Dh3W0ba-FIehQEJ6d_Bq8", "沉生而知之大空翼迷学习无敌Q");
            map.put("o9eW3wzB_aULaLH0xOMOYlOJ0ETg", "");
            UserManagerUtil.setBatchRemark(AccessTokenUtil.getValidAccessToken().getToken(),map);*/
            return MessageUtil.initTextMessage(fromUserName, toUserName, "修改备注成功");
        } else if (content.equals("4")) {
            AccessToken token = AccessTokenUtil.getValidAccessToken();
        /*    //创建分组
            GroupUtil.createGroup(token.getToken(),"{\"group\":{\"name\":\"至尊VIP\"}}");*/
           /* //更新分组名
            int res = GroupUtil.updateName(token.getToken(),"{\"group\":{\"id\":100,\"name\":\"911战队成员\"}}");*/
            //{"openid":"o9eW3w7nm2-cYoao_qiXrtrUeQ9w","to_groupid":100}
            /*//移动用户分组
            GroupUtil.moveUser(token.getToken(),"{\"openid\":\"o9eW3w7nm2-cYoao_qiXrtrUeQ9w\",\"to_groupid\":100}");
            GroupUtil.moveUser(token.getToken(),"{\"openid\":\"o9eW3w5m36mEn4uH2QLvJQjZ-nxI\",\"to_groupid\":100}");*/
            /*//批量移动用户分组
            GroupUtil.batchMoveUser(token.getToken(), "{\"openid_list\":[\"o9eW3w8Dh3W0ba-FIehQEJ6d_Bq8\",\"o9eW3wzB_aULaLH0xOMOYlOJ0ETg\",\"o9eW3w23uu7Hf5yaeSFzcG0_ZToQ\"],\"to_groupid\":100}");*/
            //查询所有分组
            List<Group> groups = GroupUtil.queryAll(token.getToken());
            groups.size();
          /*  //删除用户分组
            int result3 = GroupUtil.deleteGroup(token.getToken(), "{\"group\":{\"id\":104}}");*/
           /* //查询用户所在分组
           String groupid = GroupUtil.queryUser(token.getToken(),"{\"openid\":\"o9eW3w7nm2-cYoao_qiXrtrUeQ9w\"}");*/
        } /*else if(content.startsWith("翻译")){
            String word = content.replaceAll("^翻译", "").trim();
            if("".equals(word)){
                message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.threeMenu());
            }else{
                message = MessageUtil.initText(toUserName, fromUserName, WeixinUtil.translate(word));
            }
        }*/ else if (content.equals("?") || content.equals("？")) {
            return MessageUtil.initTextMessage(fromUserName, toUserName, MessageUtil.menuHint());
        }else if (content.contains("911")) {
            String mediaId = "3lIPHNZnAliHoT6AO9ZJEGZo9nUCFZt8M6w-7ixY2kFok9UCHyP80RcJgG0VDUil";
            return MessageUtil.initImageMessage(fromUserName,toUserName,mediaId);
        }else if (content.contains("zhangyi") || content.contains("张翼")) {
            String mediaId = "a6-HYLjErfZ3-GNixg1v3K7rPeK5tNAhr0o7NjO_wpZCxBVioYvbsInZVTsq9Hn7";
            return MessageUtil.initImageMessage(fromUserName,toUserName,mediaId);
        }else if (content.contains("chenqiang") || content.contains("陈强")) {
            String mediaId = "Ns2rgKaWyCfDLgAHkzY3j3gWv_J66pbsjMg8m2wqCWEwJ4U85D66CxF7QnKIsqT2";
            return MessageUtil.initImageMessage(fromUserName,toUserName,mediaId);
        }else if (content.contains("lianglang") || content.contains("浪")) {
            String mediaId = "Rj-3Ou2hltM59BJHkrgzn4cQ-x2H0JN-PdUrOXPT2xs9zhyxCGWlD0GU32Nvx2zA";
            return MessageUtil.initImageMessage(fromUserName,toUserName,mediaId);
        }else if (content.contains("lizhao") || content.contains("李钊")) {
            String mediaId = "x7eDbgfPVvck38SWXM-nfTy0KcXi8eczp-fZp0jEN6Q0KlXP1dk9C8IfnamMu_aA";
            return MessageUtil.initImageMessage(fromUserName,toUserName,mediaId);
        }else if (content.contains("renyue") || content.contains("任越")) {
            String mediaId = "O6Q6iUYYiTBmKOsNsetY8gqxR8Otk4s6PwPg61XkMgcdHmz3yofxzP8Y0Df0FX_9";
            return MessageUtil.initImageMessage(fromUserName,toUserName,mediaId);
        }else if (content.contains("zengqiang") || content.contains("曾强")) {
            String mediaId = "hAkBvEF5t-qcIbhM9DeSsRsamw-UL-VNB4obNUPndI-0-Jtwg67DnOpA1ZGTlGq8";
            return MessageUtil.initImageMessage(fromUserName,toUserName,mediaId);
        }else {
            UserInfo userInfo = userInfoService.selectUserInfoByOpenid(fromUserName);
            String resultContent = TuringAPIUtil.getTuringResult(content);
            logger.debug(userInfo.getNickname() + "输入的内容是：" + content);
            logger.debug("机器人返回结果：" + resultContent);
            MsgBack msgBack = new MsgBack();
            msgBack.setCreationDate(new Date());
            msgBack.setUserid(userInfo.getUserid());
            msgBack.setUserContent(content);
            msgBack.setRobotContent(resultContent);
            msgBackService.save(msgBack);
            return MessageUtil.initTextMessage(fromUserName, toUserName, resultContent);
        }

        return MessageUtil.initTextMessage(fromUserName, toUserName, "您发送的内容是：" + content);
    }

    public static String mediaHandle() {
        return null;
    }


    //事件处理
    public String eventMessageHandle(String fromUserName, String toUserName, String content, String createTime, Map<String, String> requestMap) throws IOException {
        String eventType = requestMap.get("Event");// 事件类型
        String eventKey = requestMap.get("EventKey");//自定义事件
        String respEventMessage = "";
        if (eventType.equals(Constants.EVENT_TYPE_SUBSCRIBE)) {// 订阅
            respEventMessage = MessageUtil.initTextMessage(fromUserName, toUserName, MessageUtil.menuHint());
            //每次订阅 将关注用户的信息存入数据库当中
            UserInfo userInfo = UserManagerUtil.getUserInfo(AccessTokenUtil.getValidAccessToken().getToken(), fromUserName);
            userInfoService.save(userInfo);
        } else if (eventType.equals(Constants.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅

        } else if (eventType.equals(Constants.EVENT_TYPE_CLICK)) {// 自定义菜单点击事件
            if (eventKey.equals("11")) {
                return MessageUtil.initTextMessage(fromUserName, toUserName, MessageUtil.firstMenu());
            } else if (eventKey.equals("12")) {
                return MessageUtil.initTextMessage(fromUserName, toUserName, TuringAPIUtil.getTuringResult("你好"));
            }

        } else if (eventType.equals(Constants.EVENT_TYPE_VIEW)) {
            logger.debug(fromUserName + "点击了" + eventKey);
            return MessageUtil.initTextMessage(fromUserName, toUserName, eventKey);
        } else if (eventType.equals(Constants.EVENT_TYPE_LOCATION)) {
            Location location = new Location();
            location.setFromUserName(fromUserName);
            location.setCreateTime(createTime);
            location.setLocation("纬度 " + requestMap.get("Latitude") + " 经度 " + requestMap.get("Longitude") + " 精度 " + requestMap.get("Precision"));
            logger.debug(JSONObject.toJSON(location).toString());
            // return MessageUtil.initTextMessage(fromUserName, toUserName, "您好：\n" + fromUserName + "\n您所在的位置是：" + "\n纬度 " + requestMap.get("Latitude") + "\n经度 " + requestMap.get("Longitude") + "\n精度 " + requestMap.get("Precision"));
        }
        return respEventMessage;
    }

}
