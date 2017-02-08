package pre.my.test.robot.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需的内容
 * Author:qiang.zeng on 2017/2/6.
 */
public class TuringAPIUtil {
    private static final Logger logger = LoggerFactory.getLogger(TuringAPIUtil.class);
    /**
     * 图灵机器人APIKey
     */
    private static final String TURING_KEY = "a3eef37ef0744c2d93b17eb817745a65";

    public static String getTuringResult(String content) throws IOException {

        logger.debug("用户输入的内容是：", content);

        /** 此处为图灵api接口，参数key需要自己去注册申请 */
        String apiUrl = "http://www.tuling123.com/openapi/api?key=" + TURING_KEY + "&info=";
        String url = "";
        try {
            //拼接机器人url
            url = apiUrl + URLEncoder.encode(content, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        //发送http get请求
        HttpGet request = new HttpGet(url);
        String result = "";
        CloseableHttpResponse response = null;
        try {
            response = HttpClients.createDefault().execute(request);
            /**
             * 特别注意  这一步一定要加commons-logging 这个jar包  否则会没反应，调试了好久！！
             *
             */
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                logger.debug("HttpResponse响应错误，code=" + code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response != null) {
                response.close();
            }
        }

        /**请求失败处理 */
        if (null == result) {
            return "对不起，您说的话真是太高深了……";
        }

        try {
            StringBuffer bf = new StringBuffer();
            String s = "";
            JSONObject json = JSONObject.parseObject(result);
            //以code=100000为例，参考图灵机器人api文档
            /**
             *   code   说明
             100000  文本类
             200000  链接类
             302000  新闻类
             308000  菜谱类
             */
            if (100000 == json.getInteger("code")) {
                s = json.getString("text");
                bf.append(s);
            } else if (200000 == json.getInteger("code")) {
                s = json.getString("text");
                bf.append(s);
                bf.append("\n");
                s = json.getString("url");
                bf.append(s);
            } else if (302000 == json.getInteger("code")) {
                //s = json.getString("text");
                s = "待开发，敬请期待！\n";
                bf.append(s);
            } else if (308000 == json.getInteger("code")) {
                //s = json.getString("text");
                s = "待开发,敬请期待！\n";
                bf.append(s);
            }
            result = bf.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        logger.debug("机器人返回结果：",request);
        return result;
    }
}
