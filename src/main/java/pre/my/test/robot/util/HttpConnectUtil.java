package pre.my.test.robot.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import pre.my.test.robot.dto.AccessToken;

import java.io.IOException;

/**
 * Author:qiang.zeng on 2017/1/20.
 */
public class HttpConnectUtil {

    /**
     * get请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static JSONObject doGetStr(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        JSONObject jsonObject = null;
        try {
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.parseObject(result);
            }
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return jsonObject;
    }

    /**
     * post请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static JSONObject doPostStr(String url, String outStr) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        JSONObject jsonObject = null;
        httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
        try {
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.parseObject(result);
            }
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return jsonObject;
    }

    /**
     * 获取accessToken
     * @return
     * @throws IOException
     */
    public static AccessToken getAccessToken() throws IOException {
        AccessToken accessToken = new AccessToken();
        String url = Constants.ACCESS_TOKEN_URL.replace("APPID", Constants.APP_ID).replace("APPSECRET", Constants.APP_SECRET);
        JSONObject jsonObject = doGetStr(url);
        if (jsonObject != null) {
            accessToken.setToken(jsonObject.getString("access_token"));
            accessToken.setExpiresIn(jsonObject.getInteger("expires_in"));
        }
        return accessToken;
    }

}
