import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pre.my.test.robot.dto.material.Material;
import pre.my.test.robot.util.AccessTokenUtil;
import pre.my.test.robot.util.HttpConnectUtil;

import java.io.IOException;

/**
 * Author:qiang.zeng on 2017/1/20.
 */
public class MenuTest {
    private static final Logger logger= LoggerFactory.getLogger(MenuTest.class);
    //创建菜单
    @org.junit.Test
    public void test() throws IOException {
       /* int result = MenuUtil.createMenu(AccessTokenUtil.getValidAccessToken().getToken(), MenuUtil.initMenu());
        if (result != 0) {
            logger.debug("错误码:" + result);
        } else {
            logger.debug("菜单创建成功");
        }*/


       /* List<String> list = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            String path = "C:\\Users\\Public\\Pictures\\Sample Pictures\\"+list.get(i);
            String mediaId = HttpConnectUtil.upload(path, AccessTokenUtil.getValidAccessToken().getToken(), "image");
            System.out.println(list.get(i)+" mediaId:"+mediaId);
        }*/
        String path = "C:\\Users\\Public\\Pictures\\Sample Pictures\\"+"swpu911.jpg";
        Material material = HttpConnectUtil.upload(path, AccessTokenUtil.getValidAccessToken().getToken(), "image");
        System.out.println("mediaid: "+material.getMediaId());
    }

}
