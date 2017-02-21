package pre.my.test.robot.service;

import pre.my.test.robot.dto.user.UserInfo;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/2/7.
 */
public interface IUserInfoService {
    void save(UserInfo userInfo);
    UserInfo selectUserInfoByOpenid(String openid);
    List<UserInfo> selectAll();
    List<UserInfo> selectAll(Integer  pageSize,Integer  pageNumber);
}
