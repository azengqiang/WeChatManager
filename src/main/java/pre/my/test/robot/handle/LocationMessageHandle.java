package pre.my.test.robot.handle;

import org.springframework.stereotype.Component;
import pre.my.test.robot.util.MessageUtil;

import java.util.Map;

/**
 * Author:qiang.zeng on 2017/3/27.
 */
@Component
public class LocationMessageHandle {

    public String locationMessageHandle(String fromUserName, String toUserName, Map<String, String> requestMap) {
        String label = requestMap.get("Label");
        String location_X = requestMap.get("Location_X");
        String location_Y = requestMap.get("Location_Y");
        String scale = requestMap.get("Scale");
       /* LocationMessage locationMessage = new LocationMessage();
        locationMessage.setLabel(label);
        locationMessage.setLocation_X(location_X);
        locationMessage.setLocation_Y(location_Y);
        locationMessage.setScale(scale);*/

        return MessageUtil.initTextMessage(fromUserName, toUserName, label);
    }
}
