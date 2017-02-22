package pre.my.test.robot.util;

import com.thoughtworks.xstream.XStream;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pre.my.test.robot.dto.message.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 接收普通消息工具类
 * Author:qiang.zeng@hand-china.com on 2017/1/12.
 */
public class MessageUtil {
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
    public static String musicMessageToXml(MusicMessage musicMessage) {
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
        news.setTitle("西南石油大学简介");
        news.setDescription("swpu是石油界的黄埔军校");
        news.setPicUrl(Constants.PROJECT_URL+"/resources/img/swpu.png");
       /* news.setUrl("http://www.swpu.edu.cn/");*/
        news.setUrl(Constants.PROJECT_URL+"/demo/hello?id=1");
        newses.add(news);

        News news2 = new News();
        news2.setTitle("911惊魂");
        news2.setDescription("点击查看");
        news2.setPicUrl(Constants.PROJECT_URL+"/resources/img/swpu911.jpg");
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
     * 组装图片消息
     * @param fromUserName
     * @param toUserName
     * @return
     */
    public static String initImageMessage(String fromUserName,String toUserName,String mediaId){
        String message = null;
        Image image = new Image();
        image.setMediaId(mediaId);
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(toUserName);
        imageMessage.setToUserName(fromUserName);
        imageMessage.setMsgType(Constants.MSG_TYPE_IMAGE);
        imageMessage.setCreateTime((int) new Date().getTime());
        imageMessage.setImage(image);
        message = imageMessageToXml(imageMessage);
        logger.debug(message);
        return message;
    }

    /**
     * 组装音乐消息
     * @param fromUserName
     * @param toUserName
     * @return
     */
    public static String initMusicMessage(String fromUserName ,String toUserName){
        String message = null;
        Music music = new Music();
        music.setThumbMediaId("WsHCQr1ftJQwmGUGhCP8gZ13a77XVg5Ah_uHPHVEAQuRE5FEjn-DsZJzFZqZFeFk");
        music.setTitle("薛之谦 - 丑八怪");
        music.setDescription("ugly monster");
        music.setMusicUrl(Constants.PROJECT_URL+"/resource/薛之谦 - 丑八怪.mp3");
        music.setHQMusicUrl(Constants.PROJECT_URL+"/resource/薛之谦 - 丑八怪.mp3");

        MusicMessage musicMessage = new MusicMessage();
        musicMessage.setFromUserName(toUserName);
        musicMessage.setToUserName(fromUserName);
        musicMessage.setMsgType(Constants.MSG_TYPE_MUSIC);
        musicMessage.setCreateTime((int) new Date().getTime());
        musicMessage.setMusic(music);
        message = musicMessageToXml(musicMessage);
        return message;
    }
    /**
     * 菜单提示
     *
     * @return 菜单提示内容
     */
    public static String menuHint(String userName) {
        StringBuilder builder = new StringBuilder();
        builder.append("hi,");
        builder.append(userName+",欢迎关注911公众号!\n");
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
}
