package pre.my.test.robot.controller;

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
                respMessage =locationMessageHandle(fromUserName,toUserName,requestMap);
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

    public String locationMessageHandle(String fromUserName, String toUserName,Map<String, String> requestMap) {
        String label = requestMap.get("Label");
        String location_X = requestMap.get("Location_X");
        String location_Y = requestMap.get("Location_Y");
        String scale = requestMap.get("Scale");
       /* LocationMessage locationMessage = new LocationMessage();
        locationMessage.setLabel(label);
        locationMessage.setLocation_X(location_X);
        locationMessage.setLocation_Y(location_Y);
        locationMessage.setScale(scale);*/

        return  MessageUtil.initTextMessage(fromUserName, toUserName, label);
    }

    //文本消息处理
    public String textMessageHandle(String fromUserName, String toUserName, String content) throws IOException {
        if (content.equals("1")) {

        } else if (content.equals("2")) {
            return MessageUtil.initNewsMessage(fromUserName, toUserName);
        } else if (content.equals("3")) {
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
        } else if (content.equals("?") || content.equals("？")) {
            return MessageUtil.initTextMessage(fromUserName, toUserName, MessageUtil.firstMenu());
        }/* else if (content.contains("911")) {
            String mediaId = "3lIPHNZnAliHoT6AO9ZJEGZo9nUCFZt8M6w-7ixY2kFok9UCHyP80RcJgG0VDUil";
            return MessageUtil.initImageMessage(fromUserName, toUserName, mediaId);
        }*/ else {
            UserInfo userInfo = userInfoService.selectUserInfoByOpenid(fromUserName);
            String resultContent = TuringAPIUtil.getTuringResult(content);
            logger.debug("回复内容字节：" + String.valueOf(resultContent.getBytes("utf-8").length));
            logger.debug(userInfo.getNickname() + " 输入内容：" + content);
            logger.debug("机器人回复：" + resultContent);
            MsgBack msgBack = new MsgBack();
            msgBack.setCreationDate(new Date().toString());
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
            //每次订阅 将关注用户的信息存入数据库当中
            UserInfo userInfo = UserManagerUtil.getUserInfo(AccessTokenUtil.getValidAccessToken().getToken(), fromUserName);
            userInfoService.save(userInfo);
            respEventMessage = MessageUtil.initTextMessage(fromUserName, toUserName, MessageUtil.menuHint(userInfo.getNickname()));

        } else if (eventType.equals(Constants.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅

        } else if (eventType.equals(Constants.EVENT_TYPE_CLICK)) {// 自定义菜单点击事件
            if (eventKey.equals("11")) {
                String csyb = "欢迎使用城市邮编功能！\n请编辑城市名+邮编发送至公众号，即可查询相应城市邮编\n如：内江邮编";
                return MessageUtil.initTextMessage(fromUserName, toUserName, csyb);
              /*  String url = Constants.PROJECT_URL + "/demo/hello";
                return MessageUtil.initTextMessage(fromUserName, toUserName, url);*/
            } else if (eventKey.equals("12")) {
                String tqcx = "欢迎使用天气查询功能！\n请编辑城市名+天气发送至公众号，即可查询相应城市天气\n如：内江天气";
                return MessageUtil.initTextMessage(fromUserName, toUserName, tqcx);
            } else if (eventKey.equals("13")) {
                String zyhy = "欢迎使用中英互译功能！\n请编***单词发送至公众号，即可进行单词中英互译\n如：内江单词，friend翻译";
                return MessageUtil.initTextMessage(fromUserName, toUserName, zyhy);
            } else if (eventKey.equals("21")) {
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
            //logger.debug(JSONObject.toJSON(location).toString());
            // return MessageUtil.initTextMessage(fromUserName, toUserName, "您好：\n" + fromUserName + "\n您所在的位置是：" + "\n纬度 " + requestMap.get("Latitude") + "\n经度 " + requestMap.get("Longitude") + "\n精度 " + requestMap.get("Precision"));
        }
        return respEventMessage;
    }

}
