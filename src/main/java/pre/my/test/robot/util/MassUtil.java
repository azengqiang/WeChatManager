package pre.my.test.robot.util;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * Author:qiang.zeng on 2017/4/27.
 */
public class MassUtil {
   /*       "errcode":0,
            "errmsg":"send job submission success",
            "msg_id":34182,
            "msg_data_id": 206227730*/
    public static JSONObject groupMass(String token, String massMsg) {
        String url = Constants.MASS_GROUP_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = null;
        try {
            jsonObject = HttpConnectUtil.doPostStr(url, massMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
