package pre.my.test.robot.util;

import com.thoughtworks.xstream.XStream;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import pre.my.test.robot.dto.message.TextMessage;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接收普通消息工具类
 * Author:qiang.zeng@hand-china.com on 2017/1/12.
 */
public class MessageUtil {
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


}
