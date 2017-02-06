package pre.my.test.robot.util;

import com.alibaba.fastjson.JSONObject;
import pre.my.test.robot.dto.user.UserInfo;

import java.io.IOException;

/**
 * Author:qiang.zeng on 2017/2/6.
 */
public class UserInfoUtil {
    public static UserInfo setUserInfo(String token,String openid) throws IOException {
        UserInfo userInfo = null;
        String url = Constants.USER_INFO_URL.replace("ACCESS_TOKEN", token).replace("OPENID",openid);
        JSONObject jsonObject = HttpConnectUtil.doGetStr(url);
        if (jsonObject != null) {
            userInfo = new UserInfo();
            userInfo.setSubscribe(jsonObject.getString("subscribe"));
            userInfo.setOpenid(openid);
            userInfo.setNickname(jsonObject.getString("nickName"));
            userInfo.setSex(jsonObject.getString("sex"));
            userInfo.setLanguage(jsonObject.getString("language"));
            userInfo.setCity(jsonObject.getString("city"));
            userInfo.setProvince(jsonObject.getString("province"));
            userInfo.setCountry(jsonObject.getString("country"));
            userInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
            userInfo.setSubscribe_time(jsonObject.getString("subscribe_time"));
            userInfo.setUnionid(jsonObject.getString("unionid"));
            userInfo.setRemark(jsonObject.getString("remark"));
            userInfo.setGroupid(jsonObject.getString("groupid"));
        }
        return userInfo;
    }
}
