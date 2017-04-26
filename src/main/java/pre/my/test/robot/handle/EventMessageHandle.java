package pre.my.test.robot.handle;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pre.my.test.robot.dto.autoresponse.AutoResponseMessage;
import pre.my.test.robot.dto.location.RimLocation;
import pre.my.test.robot.dto.user.Group;
import pre.my.test.robot.dto.user.Location;
import pre.my.test.robot.dto.user.SubscribeDetail;
import pre.my.test.robot.dto.user.UserInfo;
import pre.my.test.robot.service.*;
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
    private IAutoResponseService autoResponseService;
    @Autowired
    private IRimLocationService rimLocationService;
    @Autowired
    private ISubscribeDetailService subscribeDetailService;
    @Autowired
    private IGroupService groupService;
    private String lon;
    private String lat;

    //事件处理
    public String eventMessageHandle(String fromUserName, String toUserName, String content, String createTime, Map<String, String> requestMap) throws IOException {
        String eventType = requestMap.get("Event");// 事件类型
        String eventKey = requestMap.get("EventKey");//自定义事件
        String respEventMessage = "";
        if (eventType.equals(Constants.EVENT_TYPE_SUBSCRIBE)) {// 订阅
            //每次订阅 首先查数据库是否有这个人 有，修改关注状态为1，否则，将关注用户的信息存入数据库当中
            UserInfo existUserInfo = userInfoService.selectUserInfoByOpenid(fromUserName);
            if (existUserInfo != null) {
                existUserInfo.setSubscribe("1");
                existUserInfo.setGroupid("0");
                userInfoService.update(existUserInfo);
                logger.debug(existUserInfo.getNickname() + "重新关注");
            } else {
                UserInfo newUserInfo = UserManagerUtil.getUserInfo(AccessTokenUtil.getValidAccessToken().getToken(), fromUserName);
                userInfoService.save(newUserInfo);
                logger.debug(newUserInfo.getNickname(), "新关注");
            }

            SubscribeDetail subscribeDetail = new SubscribeDetail();
            subscribeDetail.setOpenid(fromUserName);
            subscribeDetail.setAction("subscribe");
            subscribeDetailService.save(subscribeDetail);
            //设置用户关注后公众号推送消息
            String responseMessage = "欢迎关注swpu911公众号！";
            List<AutoResponseMessage> autoResponseMessages = autoResponseService.select(new AutoResponseMessage(null, "关注回复语", null));
            if (autoResponseMessages != null && autoResponseMessages.size() != 0) {
                responseMessage = autoResponseMessages.get(0).getResponseMsg();
            }
            Group originGroup = groupService.select(new Group("0", null, null));
            String originCount = String.valueOf(Integer.valueOf(originGroup.getCount())+1);
            originGroup.setCount(originCount);
            groupService.updateCount(originGroup);
            respEventMessage = MessageUtil.initTextMessage(fromUserName, toUserName, responseMessage);

        } else if (eventType.equals(Constants.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅
            UserInfo existUserInfo = userInfoService.selectUserInfoByOpenid(fromUserName);
            existUserInfo.setSubscribe("0");
            existUserInfo.setGroupid("-1");
            userInfoService.update(existUserInfo);
            resizeGroup();
            logger.debug(existUserInfo.getNickname() + "取消关注");
            SubscribeDetail subscribeDetail = new SubscribeDetail();
            subscribeDetail.setOpenid(fromUserName);
            subscribeDetail.setAction("unSubscribe");
            subscribeDetailService.save(subscribeDetail);
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
                return MessageUtil.initTextMessage(fromUserName, toUserName, TuringAPIUtil.getTuringResult("hello"));
            } else if (eventKey.equals("31")) {
               /* TuringLocation turingLocation = new TuringLocation();
                String address = TuringAPIUtil.changeAddr(lon, lat);
                turingLocation.setLoc(address);
                turingLocation.setInfo("附近的餐厅");
                turingLocation.setUserid("1");
                turingLocation.setLon(lon);
                turingLocation.setLat(lat);
                return MessageUtil.initTextMessage(fromUserName, toUserName, TuringAPIUtil.getTuringRestaurantResult(turingLocation));*/
                String mstj = "请发送您的位置，我们会为您推荐附近的餐厅，祝您吃的愉快！！";
                RimLocation rimLocation = new RimLocation();
                rimLocation.setOpenid(fromUserName);
                rimLocation.setRimType("restaurant");
                rimLocationService.save(rimLocation);
                return MessageUtil.initTextMessage(fromUserName, toUserName, mstj);
            } else if (eventKey.equals("32")) {
               /* TuringLocation turingLocation = new TuringLocation();
                String address = TuringAPIUtil.changeAddr(lon, lat);
                turingLocation.setLoc(address);
                turingLocation.setInfo("附近的酒店");
                turingLocation.setUserid("1");
                return MessageUtil.initTextMessage(fromUserName, toUserName, TuringAPIUtil.getTuringRestaurantResult(turingLocation));*/
                String mstj = "请发送您的位置，我们会为您推荐附近的酒店，祝您旅途愉快！！";
                RimLocation rimLocation = new RimLocation();
                rimLocation.setOpenid(fromUserName);
                rimLocation.setRimType("hotel");
                rimLocationService.save(rimLocation);
                return MessageUtil.initTextMessage(fromUserName, toUserName, mstj);
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
            lon = requestMap.get("Longitude");
            lat = requestMap.get("Latitude");
            // return MessageUtil.initTextMessage(fromUserName, toUserName, "您好：\n" + fromUserName + "\n您所在的位置是：" + "\n纬度 " + requestMap.get("Latitude") + "\n经度 " + requestMap.get("Longitude") + "\n精度 " + requestMap.get("Precision"));
        }
        return respEventMessage;
    }
    private void resizeGroup() throws IOException {
        List<Group> groups = GroupUtil.queryAll(AccessTokenUtil.getValidAccessToken().getToken());
        for (Group group : groups) {
            groupService.updateCount(group);
        }
        logger.debug("分组数量发送变化");
    }
}





