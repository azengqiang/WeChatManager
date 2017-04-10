package pre.my.test.robot.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pre.my.test.robot.dto.AccessToken;
import pre.my.test.robot.dto.user.Remark;
import pre.my.test.robot.dto.user.UserInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:qiang.zeng on 2017/2/6.
 */
public class UserManagerUtil {
    /**
     * 根据openid获取用户基本信息
     *
     * @param token
     * @param openid
     * @return 用户基本信息
     * @throws IOException
     */
    public static UserInfo getUserInfo(String token, String openid) throws IOException {
        UserInfo userInfo = null;
        String url = Constants.USER_INFO_URL.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
        JSONObject jsonObject = HttpConnectUtil.doGetStr(url);
        if (jsonObject != null) {
            userInfo = new UserInfo();
            userInfo.setSubscribe(jsonObject.getString("subscribe"));
            userInfo.setOpenid(openid);
            userInfo.setNickname(jsonObject.getString("nickname"));
            userInfo.setSex(jsonObject.getString("sex"));
            userInfo.setLang(jsonObject.getString("language"));
            userInfo.setAddress(jsonObject.getString("country") + " " + jsonObject.getString("province") + " " + jsonObject.getString("city"));
            userInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
            userInfo.setSubscribe_time(jsonObject.getString("subscribe_time"));
            userInfo.setUnionid(jsonObject.getString("unionid"));
            userInfo.setRemark(jsonObject.getString("remark"));
            userInfo.setGroupid(jsonObject.getString("groupid"));
        }
        return userInfo;
    }

    /**
     * 获取关注用户openid列表
     *
     * @param token
     * @return 关注用户openid列表
     * @throws IOException
     */
    public static List<String> getUserInfoList(String token) throws IOException {
        List<String> infoList = new ArrayList<>();
        String url = Constants.USER_INFO_LIST_URL.replace("ACCESS_TOKEN", token).replace("&next_openid=NEXT_OPENID", "");
        JSONObject jsonObject = HttpConnectUtil.doGetStr(url);
        if (jsonObject != null) {
            jsonObject.getString("total");
            jsonObject.getString("count");
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray openidList = data.getJSONArray("openid");
            for (int i = 0; i < openidList.size(); i++) {
                infoList.add((String) openidList.get(i));
            }
            jsonObject.getString("next_openid");
        }

        return infoList;
    }

    /**
     * 设置关注用户的备注名
     *
     * @param token
     * @param remark 新的备注名实体
     * @return
     * @throws IOException
     */
    public static int setRemark(String token, Remark remark) throws IOException {
        int result = 0;
        String url = Constants.USER_REMARK_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = HttpConnectUtil.doPostStr(url, JSONObject.toJSON(remark).toString());
        if (jsonObject != null) {
            result = jsonObject.getInteger("errcode");
        }
        return result;
    }

    public static void setBatchRemark(String token, List<Remark> remarks) throws IOException {
        for (Remark remark:remarks) {
            setRemark(token, remark);
        }
    }

    public static List<UserInfo> getAllUserInfo() throws IOException {
        AccessToken token = AccessTokenUtil.getValidAccessToken();
        List<String> list = UserManagerUtil.getUserInfoList(token.getToken());
        List<UserInfo> userInfos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            UserInfo userInfo = UserManagerUtil.getUserInfo(token.getToken(), list.get(i));
            userInfos.add(userInfo);
        }
        return userInfos;
    }
}
