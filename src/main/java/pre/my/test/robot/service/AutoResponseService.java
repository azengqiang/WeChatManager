package pre.my.test.robot.service;

import pre.my.test.robot.dto.autoresponse.AutoResponseMessage;
import pre.my.test.robot.dto.user.UserInfo;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/21.
 */
public interface AutoResponseService {
    void save(AutoResponseMessage autoResponseMessage);

    void delete(AutoResponseMessage autoResponseMessage);

    List<AutoResponseMessage> select(AutoResponseMessage autoResponseMessage);
}
