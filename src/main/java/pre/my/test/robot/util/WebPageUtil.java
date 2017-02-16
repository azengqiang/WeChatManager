package pre.my.test.robot.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pre.my.test.robot.dto.user.WebPageUserInfo;

import java.io.IOException;

/**
 * Author:qiang.zeng on 2017/2/13.
 */
public class WebPageUtil {
    private static final Logger logger = LoggerFactory.getLogger(WebPageUtil.class);

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
            logger.debug(jsonObject.toJSONString());
        }

        return jsonObject;
    }

    public static WebPageUserInfo getUserInfo(String accessToken, String openid) {
        String url = Constants.WEB_PAGE_USER_INFO.replace("ACCESS_TOKEN", accessToken).replace("SECRET", openid);
        WebPageUserInfo userInfo = null;
        JSONObject jsonObject = null;
        try {
            jsonObject = HttpConnectUtil.doGetStr(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonObject != null) {
            userInfo = new WebPageUserInfo();
            userInfo.setOpenid(jsonObject.getString("openid"));
            userInfo.setNickname(jsonObject.getString("nickname"));
            userInfo.setSex(jsonObject.getString("sex"));
            userInfo.setAddress(jsonObject.getString("country") + " " + jsonObject.getString("province") + " " + jsonObject.getString("city"));
            userInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
            userInfo.setUnionid(jsonObject.getString("unionid"));
            userInfo.setPrivilege(jsonObject.getJSONArray("privilege").toString());
            logger.debug(jsonObject.toJSONString());
        }
        return userInfo;
    }
}
