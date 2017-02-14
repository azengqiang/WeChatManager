package pre.my.test.robot.util;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * Author:qiang.zeng on 2017/2/13.
 */
public class WebPageUtil {
    public static String getcode(String appid, String redirectUrl, String scope, String state) {
        String url = Constants.WEB_PAGE_CODE.replace("APPID", appid).replace("REDIRECT_URI", redirectUrl).replace("SCOPE", scope).replace("STATE", state);
        return url;
    }

    public static JSONObject getAccessToken(String appid, String secret, String code) {
        String url = Constants.WEB_PAGE_ACCESS_TOKEN.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
        JSONObject jsonObject = null;
        try {
            jsonObject = HttpConnectUtil.doGetStr(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonObject != null) {
            String accessToken = jsonObject.getString("access_token");
            String openid = jsonObject.getString("openid");
        }

        return jsonObject;
    }

    public static JSONObject getUserInfo(String accessToken, String openid) {
        String url = Constants.WEB_PAGE_USER_INFO.replace("ACCESS_TOKEN", accessToken).replace("SECRET", openid);
        JSONObject jsonObject = null;
        try {
            jsonObject = HttpConnectUtil.doGetStr(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonObject != null) {
            jsonObject.getString("openid");
            jsonObject.getString("nickname");
            jsonObject.getString("sex");
            jsonObject.getString("province");
            jsonObject.getString("city");
            jsonObject.getString("country");
            jsonObject.getString("headimgurl");
            jsonObject.getJSONArray("privilege");
            jsonObject.getString("unionid");
        }
        return jsonObject;
    }
}
