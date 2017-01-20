import com.alibaba.fastjson.JSONObject;
import pre.my.test.robot.dto.AccessToken;
import pre.my.test.robot.util.HttpConnectUtil;

import java.io.IOException;

/**
 * Author:qiang.zeng on 2017/1/20.
 */
public class MenuTest {
    //创建菜单
    @org.junit.Test
    public void test() throws IOException {
        AccessToken token = HttpConnectUtil.getAccessToken();
        System.out.println("票据：" + token.getToken());
        System.out.println("有效时间：" + token.getExpiresIn());
        String menu = JSONObject.toJSON(HttpConnectUtil.initMenu()).toString();
        int result = HttpConnectUtil.createMenu(token.getToken(), menu);
        if (result != 0) {
            System.out.println("错误码:" + result);
        } else {
            System.out.println("菜单创建成功");
        }
    }
}
