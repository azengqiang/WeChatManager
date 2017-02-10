package pre.my.test.robot.util;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pre.my.test.robot.dto.AccessToken;
import pre.my.test.robot.dto.message.*;
import pre.my.test.robot.dto.user.Group;
import pre.my.test.robot.dto.user.Location;
import pre.my.test.robot.dto.user.UserInfo;
import pre.my.test.robot.service.IUserInfoService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 接收普通消息工具类
 * Author:qiang.zeng@hand-china.com on 2017/1/12.
 */
public class MessageUtil {
    @Autowired
    private static IUserInfoService service;
    private static final Logger logger = LoggerFactory.getLogger(MessageUtil.class);

    /**
     * 将xml解析为Map集合
     *
     * @param request HttpServletRequest
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        InputStream ins = null;
        try {
            ins = request.getInputStream();
            SAXReader saxReader = new SAXReader();
            //读取输入流 获取document文档
            Document document = saxReader.read(ins);
            Element rootElement = document.getRootElement();
            //获取document文档中的元素
            List<Element> elements = rootElement.elements();
            for (Element element : elements) {
                //document文档中的元素信息放入map
                map.put(element.getName(), element.getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            //关闭输入流
            IOUtils.closeQuietly(ins);
        }
        return map;

    }

    /**
     * 将文本信息转换为xml
     *
     * @param textMessage 文本信息
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml", textMessage.getClass());
        return xStream.toXML(textMessage);
    }

    /**
     * 图文消息转为xml
     *
     * @param newsMessage 图文消息
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
        XStream xstream = new XStream();
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new News().getClass());
        return xstream.toXML(newsMessage);
    }

    /**
     * 图片消息转为xml
     *
     * @param imageMessage 图片消息
     * @return
     */
    public static String imageMessageToXml(ImageMessage imageMessage) {
        XStream xstream = new XStream();
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }

    /**
     * 音乐消息转为xml
     *
     * @param musicMessage 音乐消息
     * @return
     */
    public static String musicMessageToXml(MusicMessage  musicMessage) {
        XStream xstream = new XStream();
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /**
     * 组装文本信息
     *
     * @param fromUserName 发起用户
     * @param toUserName   接收用户
     * @param content      文本内容
     * @return xml string
     */
    public static String initTextMessage(String fromUserName, String toUserName, String content) {
        TextMessage textMessage = new TextMessage();
        textMessage.setCreateTime((int) new Date().getTime());
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setMsgType(Constants.MSG_TYPE_TEXT);
        textMessage.setContent(content);
        return textMessageToXml(textMessage);
    }

    /**
     * 组装文本信息
     *
     * @param fromUserName 发起用户
     * @param toUserName   接收用户
     * @return xml string
     */
    public static String initNewsMessage(String fromUserName, String toUserName) {
        String message = null;
        List<News> newses = new ArrayList<>();
        NewsMessage newsMessage = new NewsMessage();

        News news = new News();
        news.setTitle("西南右油大学简介");
        news.setDescription("西南右油大学是一所石油院校");
        news.setPicUrl("http://53e784ce.tunnel.qydev.com/resources/img/swpu.png");
       /* news.setUrl("http://www.swpu.edu.cn/");*/
        news.setUrl("http://53e784ce.tunnel.qydev.com/demo/hello?id=1");
        newses.add(news);

        News news2 = new News();
        news2.setTitle("19栋611惊魂");
        news2.setDescription("详情请点击查看");
        news2.setPicUrl("http://53e784ce.tunnel.qydev.com/resources/img/swpu911.jpg");
        news2.setUrl("http://www.ziyuanmao.com/#/home");
        newses.add(news2);

        newsMessage.setFromUserName(toUserName);
        newsMessage.setToUserName(fromUserName);
        newsMessage.setArticles(newses);
        newsMessage.setArticleCount(newses.size());
        newsMessage.setCreateTime((int) new Date().getTime());
        newsMessage.setMsgType(Constants.MSG_TYPE_NEWS);
        message = newsMessageToXml(newsMessage);
        return message;
    }

    /**
     * 菜单提示
     *
     * @return 菜单提示内容
     */
    public static String menuHint() {
        StringBuilder builder = new StringBuilder();
        builder.append("欢迎您关注西南右油911公众号!\n");
      /*  builder.append("请按以下菜单进行操作：\n");
        builder.append("1.西南右油911公众号介绍\n");
        builder.append("2.西南右油911成员介绍\n");
        builder.append("3.內江一中陪聊机器人\n");
        builder.append("4.其他，调出主菜单\n");
        builder.append("请回复对应序号，进行相应操作\n");*/
        return builder.toString();
    }

    public static String firstMenu() {
        StringBuilder builder = new StringBuilder();
        builder.append("请输入要查看的成员编号：\n");
        builder.append("1. 无敌我q：李钊\n");
        builder.append("2. 土豪我越：任越\n");
        builder.append("3. 骚浪我弟：梁浪\n");
        builder.append("4. 学神我翼：张翼\n");
        builder.append("5. 大佬我哥：陈强\n");
        builder.append("6. 渣渣如我：曾强\n");
        return builder.toString();
    }

    //文本消息处理
    public static String textMessageHandle(String fromUserName, String toUserName, String content) throws IOException {
        if (content.equals("1")) {
            /*AccessToken token = AccessTokenUtil.getValidAccessToken();
            List<String> list= UserManagerUtil.getUserInfoList(token.getToken());
            for(int i=0;i<list.size();i++){
                UserInfo userInfo = UserManagerUtil.getUserInfo(token.getToken(), list.get(i));
                service.save(userInfo);
            }*/
        } else if (content.equals("2")) {
            return initNewsMessage(fromUserName, toUserName);
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
        } else if (content.equals("?") || content.equals("？")) {
            return MessageUtil.initTextMessage(fromUserName, toUserName, MessageUtil.menuHint());
        } else {
            return MessageUtil.initTextMessage(fromUserName, toUserName, TuringAPIUtil.getTuringResult(content));
        }

        return MessageUtil.initTextMessage(fromUserName, toUserName, "您发送的内容是：" + content);
    }

    public static String mediaHandle() {
        return null;
    }


    //事件处理
    public static String eventMessageHandle(String fromUserName, String toUserName, String content, String createTime, Map<String, String> requestMap) throws IOException {
        String eventType = requestMap.get("Event");// 事件类型
        String eventKey = requestMap.get("EventKey");//自定义事件
        String respEventMessage = "";
        if (eventType.equals(Constants.EVENT_TYPE_SUBSCRIBE)) {// 订阅
            respEventMessage = MessageUtil.initTextMessage(fromUserName, toUserName, MessageUtil.menuHint());
            //每次订阅 将关注用户的信息存入数据库当中
            UserInfo userInfo = UserManagerUtil.getUserInfo(AccessTokenUtil.getValidAccessToken().getToken(), fromUserName);
            service.save(userInfo);
        } else if (eventType.equals(Constants.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅

        } else if (eventType.equals(Constants.EVENT_TYPE_CLICK)) {// 自定义菜单点击事件
            if (eventKey.equals("11")) {
                return MessageUtil.initTextMessage(fromUserName, toUserName, MessageUtil.firstMenu());
            } else if (eventKey.equals("12")) {
                return MessageUtil.initTextMessage(fromUserName, toUserName, TuringAPIUtil.getTuringResult("你好"));
            }

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
