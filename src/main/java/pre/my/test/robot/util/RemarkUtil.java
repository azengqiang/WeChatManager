package pre.my.test.robot.util;

import com.alibaba.fastjson.JSONObject;
import pre.my.test.robot.dto.user.Remark;

import java.io.IOException;
import java.util.Map;

/**
 * Author:qiang.zeng on 2017/2/8.
 */
public class RemarkUtil {
    public static void setRemark(String token,Map<String,String> map) throws IOException {
        Remark remark = new Remark();
        //post的新备注名json数据
        String remarkName = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            remark.setOpenid(entry.getKey());
            remark.setRemark(entry.getValue());
            remarkName = JSONObject.toJSON(remark).toString();
            UserManagerUtil.setRemark(token, remarkName);
        }

    }
}
