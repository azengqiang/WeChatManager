package pre.my.test.robot.service;

import pre.my.test.robot.dto.user.MsgBack;
import pre.my.test.robot.dto.user.UserInfo;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/2/10.
 */
public interface IMsgBackService {
    void save(MsgBack msgBack);
    int selectCount();
    List<UserInfo> selectAllMsgBack(Integer  pageSize,Integer  pageNumber);
}
