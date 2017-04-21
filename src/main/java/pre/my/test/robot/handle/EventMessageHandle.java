package pre.my.test.robot.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pre.my.test.robot.dto.autoresponse.AutoResponseMessage;
import pre.my.test.robot.dto.user.Location;
import pre.my.test.robot.dto.user.UserInfo;
import pre.my.test.robot.service.AutoResponseService;
import pre.my.test.robot.service.IUserInfoService;
import pre.my.test.robot.util.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Author:qiang.zeng on 2017/3/27.
 */
@Component
public class EventMessageHandle {
    private static final Logger logger = LoggerFactory.getLogger(EventMessageHandle.class);
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private AutoResponseService service;


    //事件处理
    public String eventMessageHandle(String fromUserName, String toUserName, String content, String createTime, Map<String, String> requestMap) throws IOException {
        String eventType = requestMap.get("Event");// 事件类型
        String eventKey = requestMap.get("EventKey");//自定义事件
        String respEventMessage = "";
        if (eventType.equals(Constants.EVENT_TYPE_SUBSCRIBE)) {// 订阅
            //每次订阅 将关注用户的信息存入数据库当中
            UserInfo userInfo = UserManagerUtil.getUserInfo(AccessTokenUtil.getValidAccessToken().getToken(), fromUserName);
            userInfoService.save(userInfo);
            //设置用户关注后公众号推送消息
            String responseMessage = "欢迎关注swpu911公众号！";
            List<AutoResponseMessage> autoResponseMessages = service.select(new AutoResponseMessage("关注回复语", null));
            if (autoResponseMessages != null && autoResponseMessages.size() != 0) {
                responseMessage = autoResponseMessages.get(0).getResponseMsg();
            }
            respEventMessage = MessageUtil.initTextMessage(fromUserName, toUserName, responseMessage);

        } else if (eventType.equals(Constants.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅
            UserInfo userInfo = userInfoService.selectUserInfoByOpenid(fromUserName);
            if (userInfo != null) {
                userInfoService.delete(userInfo);
            }
            logger.debug(userInfo.getNickname()+"取消了关注");
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
