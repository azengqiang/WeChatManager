package pre.my.test.robot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pre.my.test.robot.dto.location.RimLocation;
import pre.my.test.robot.dto.user.TuringLocation;
import pre.my.test.robot.service.IRimLocationService;

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
    //百度地图经纬度转换URL
    private static String addrUrl = "http://api.map.baidu.com/geocoder/v2/?ak=TbO4of5ZfKAVGSqd8gxuiDTNtg9N280Y&location=lat,lon&output=json&pois=0";

    private static String address = "";

    public static String changeAddr(String lon, String lat) throws IOException {
        addrUrl = addrUrl.replace("lon", lon).replace("lat", lat);
        HttpGet request = new HttpGet(addrUrl);
        CloseableHttpResponse response = HttpClients.createDefault().execute(request);
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        JSONObject json = JSONObject.parseObject(result);
        JSONObject res = json.getJSONObject("result");
        //sematic_description
        address = res.getString("formatted_address");
        logger.debug("用户地址：{}", address);
        return address;
    }

    public static String getTuringRestaurantResult(TuringLocation turingLocation) throws IOException {

        /** 此处为图灵api接口，参数key需要自己去注册申请 */
        String apiUrl = "http://www.tuling123.com/openapi/api";

        //发送http get请求
        turingLocation.setKey(TURING_KEY);
        String outStr = JSON.toJSONString(turingLocation);
        logger.debug("周边用户位置：{}",outStr);
        HttpPost httpPost = new HttpPost(apiUrl);
        httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
        String result = "";
        CloseableHttpResponse response = null;
        try {
            response = HttpClients.createDefault().execute(httpPost);
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                logger.debug("HttpResponse响应错误，code={}", code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
        return result;
    }

    public static String getTuringResult(String content) throws IOException {

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
                logger.debug("HttpResponse响应错误，code={}", code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
        return result;
    }


}
