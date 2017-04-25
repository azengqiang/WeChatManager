package pre.my.test.robot.handle;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pre.my.test.robot.dto.location.RimLocation;
import pre.my.test.robot.dto.message.LocationMessage;
import pre.my.test.robot.dto.user.TuringLocation;
import pre.my.test.robot.service.IRimLocationService;
import pre.my.test.robot.util.MessageUtil;
import pre.my.test.robot.util.TuringAPIUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Author:qiang.zeng on 2017/3/27.
 */
@Component
public class LocationMessageHandle {
    @Autowired
    private IRimLocationService rimLocationService;

    public String locationMessageHandle(String fromUserName, String toUserName, Map<String, String> requestMap) throws IOException {
        String label = requestMap.get("Label");
        String location_X = requestMap.get("Location_X");
        String location_Y = requestMap.get("Location_Y");
        String scale = requestMap.get("Scale");
        LocationMessage locationMessage = new LocationMessage();
        locationMessage.setLabel(label);
        //纬度 31.169786 经度 121.151558
        locationMessage.setLocation_X(location_X);
        locationMessage.setLocation_Y(location_Y);
        locationMessage.setScale(scale);

        RimLocation rimLocation = new RimLocation();
        rimLocation.setOpenid(fromUserName);
        List<RimLocation> rimLocations = rimLocationService.select(rimLocation);
        if (rimLocations == null || rimLocations.size() == 0) {
            return MessageUtil.initTextMessage(fromUserName, toUserName, "请先通过菜单（周边->附近餐厅/附近酒店）,选择需要的服务，谢谢！！");
        }
        TuringLocation turingLocation = new TuringLocation();
        RimLocation rl = rimLocations.get(0);
        if ("restaurant".equalsIgnoreCase(rl.getRimType())) {
            turingLocation.setLon(location_Y);
            turingLocation.setLat(location_X);
            turingLocation.setInfo("附近的餐厅");
            turingLocation.setLoc(label);
            turingLocation.setUserid("1");
        } else if ("hotel".equalsIgnoreCase(rl.getRimType())) {
            turingLocation.setInfo("附近的酒店");
            turingLocation.setLoc(label);
            turingLocation.setUserid("1");
        }
        String callback = TuringAPIUtil.getTuringRestaurantResult(turingLocation);
        rl.setAddress(label);
        rl.setCallBack(callback);
        rimLocationService.update(rl);
        //如果用户多次点击周边推荐，数据库有多条空记录，在发送位置过后，取最新的一条回调，把剩下的空记录填上
        RimLocation reset = new RimLocation();
        reset.setOpenid(fromUserName);
        List<RimLocation> resetList = rimLocationService.select(reset);
        if (resetList != null && resetList.size() != 0) {
            for (RimLocation r : resetList) {
                r.setAddress(label);
                r.setCallBack("多次点击覆盖");
                rimLocationService.update(r);
            }
        }

        return MessageUtil.initTextMessage(fromUserName, toUserName, callback);
     /*   return MessageUtil.initTextMessage(fromUserName, toUserName, label);*/
    }
}
