import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pre.my.test.robot.dto.AccessToken;
import pre.my.test.robot.util.AccessTokenUtil;
import pre.my.test.robot.util.MenuUtil;

import java.io.IOException;

/**
 * Author:qiang.zeng on 2017/1/20.
 */
public class MenuTest {
    private static final Logger logger= LoggerFactory.getLogger(MenuTest.class);
    //创建菜单
    @org.junit.Test
    public void test() throws IOException {
        AccessToken token = AccessTokenUtil.getValidAccessToken();
        int result = MenuUtil.createMenu(token.getToken(), MenuUtil.initMenu());
        if (result != 0) {
            logger.debug("错误码:" + result);
        } else {
            logger.debug("菜单创建成功");
        }
    }
}
