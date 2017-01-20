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
import pre.my.test.robot.dto.menu.Button;
import pre.my.test.robot.dto.menu.ClickButton;
import pre.my.test.robot.dto.menu.Menu;
import pre.my.test.robot.dto.menu.ViewButton;

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

    /**
     * 组装菜单
     * @return
     */
    public static Menu initMenu() {
        Menu menu = new Menu();
        ClickButton button11 = new ClickButton();
        button11.setName("911成员介绍");
        button11.setType(Constants.MENU_TYPE_CLICK);
        button11.setKey("11");
        Button button1 = new Button();
        button1.setName("走近911");
        button1.setSub_button(new Button[]{button11});

        ViewButton button21 = new ViewButton();
        button21.setName("百度一下");
        button21.setType(Constants.MENU_TYPE_VIEW);
        button21.setUrl("http://www.baidu.com");
        Button button2 = new Button();
        button2.setName("网页");
        button2.setSub_button(new Button[]{button21});

        ClickButton button31 = new ClickButton();
        button31.setName("二维码扫描");
        button31.setType(Constants.MENU_TYPE_SCAN_CODE_PUSH);
        button31.setKey("31");
        ClickButton button32 = new ClickButton();
        button32.setName("地理位置");
        button32.setType(Constants.MENU_TYPE_LOCATION_SELECT);
        button32.setKey("32");
        Button button3 = new Button();
        button3.setName("日常");
        button3.setSub_button(new Button[]{button31, button32});

        menu.setButton(new Button[]{button1, button2, button3});
        return menu;
    }

    /**
     * 创建菜单
     *
     * @param token access_token
     * @param menu  需要创建的菜单json数据 String
     * @return 微信返回的json数据 errcode 0表示成功
     * @throws IOException
     */
    public static int createMenu(String token, String menu) throws IOException {
        int result = 0;
        String url = Constants.MENU_CREATE_URL.replace("ACCESS_TOKEN", token);
        //post请求创建菜单
        JSONObject jsonObject = doPostStr(url, menu);
        if (jsonObject != null) {
            result = jsonObject.getInteger("errcode");
        }
        return result;
    }
    public static  JSONObject queryMenu(String token) throws IOException {
        String url = Constants.MENU_QUERY_URL.replace("ACCESS_TOKEN", token);
        return doGetStr(url);
    }
    public static  int deleteMenu(String token) throws IOException {
        int result = 0;
        String url = Constants.MENU_DELETE_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = doGetStr(url);
        if (jsonObject != null) {
            result = jsonObject.getInteger("errcode");
        }
        return result;
    }
}
