import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pre.my.test.robot.dto.mass.*;
import pre.my.test.robot.dto.material.Material;
import pre.my.test.robot.dto.message.Image;
import pre.my.test.robot.util.AccessTokenUtil;
import pre.my.test.robot.util.Constants;
import pre.my.test.robot.util.HttpConnectUtil;
import pre.my.test.robot.util.MassUtil;

import java.io.IOException;

/**
 * Author:qiang.zeng on 2017/1/20.
 */
public class MassTest {
    private static final Logger logger = LoggerFactory.getLogger(MassTest.class);

    //创建菜单
    @org.junit.Test
    public void test() throws IOException {
        String massMsg = null;
        TextMass textMass = new TextMass();
        textMass.setText(new MText("测试！！来自swpu911群发"));
        textMass.setFilter(new Filter(false, "0"));
        textMass.setMsgtype("text");
         massMsg =  JSON.toJSONString(textMass);

     /*  ImageMass imageMass = new ImageMass();
        Filter filter= new Filter();
        filter.setIs_to_all(true);
        filter.setGroup_id("0");
        imageMass.setFilter(filter);
        imageMass.setMsgtype(Constants.MASS_MAG_TYPE_IMAGE);
        imageMass.setImage(new MImage("IKj-yH_Yv8SQ-YBDmc-hILP8FcK3T4H8KUtCQRxr13A"));
        massMsg =  JSON.toJSONString(imageMass);
        massMsg = "{\"filter\":{\"group_id\":\"0\",\"is_to_all\":true},\"image\":{\"media_id\":\"IKj-yH_Yv8SQ-YBDmc-hIH-H33_UTNaUbC44P5anWZs\"},\"msgtype\":\"image\"}";*/
        logger.debug("massMsg: {}", massMsg);
        JSONObject jsonObject = MassUtil.groupMass(AccessTokenUtil.getValidAccessToken().getToken(), massMsg);
        if (jsonObject.getInteger("errcode") == 0) {
            logger.debug("群发成功 {}", jsonObject.toJSONString());
        } else {
            logger.debug("群发失败 {}",jsonObject.toJSONString());
        }
    }

}
