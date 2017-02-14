package pre.my.test.robot.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pre.my.test.robot.dto.user.Remark;
import pre.my.test.robot.dto.user.UserInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author:qiang.zeng on 2017/2/6.
 */
public class UserManagerUtil {
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

    public static List<String> getUserInfoList(String token) throws IOException {
        List<String> infoList = new ArrayList<>();
        String url = Constants.USER_INFO_LIST_URL.replace("ACCESS_TOKEN", token).replace("&next_openid=NEXT_OPENID", "");
        JSONObject jsonObject = HttpConnectUtil.doGetStr(url);
        if (jsonObject != null) {
            jsonObject.getString("total");
            jsonObject.getString("count");
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray openidList  =data.getJSONArray("openid");
            for(int i=0;i<openidList.size();i++){
                infoList.add((String) openidList.get(i));
            }
            jsonObject.getString("next_openid");
        }

        return infoList;
    }

    /**
     * 设置关注用户的备注名
     * @param token
     * @param remark 新的备注名实体
     * @return
     * @throws IOException
     */
    public static int setRemark(String token,Remark remark) throws IOException {
        int result = 0;
        String url = Constants.USER_REMARK_URL.replace("ACCESS_TOKEN", token);
        String remarkJson = JSONObject.toJSON(remark).toString();
        JSONObject jsonObject = HttpConnectUtil.doPostStr(url, remarkJson);
        if (jsonObject != null) {
            result = jsonObject.getInteger("errcode");
        }
        return result;
    }
    public static void setBatchRemark(String token,Map<String,String> map) throws IOException {
        Remark remark = new Remark();
        //post的新备注名json数据
        String remarkName = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            remark.setOpenid(entry.getKey());
            remark.setRemark(entry.getValue());
            setRemark(token, remark);
        }

    }
}
