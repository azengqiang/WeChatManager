package pre.my.test.robot.mapper;

import pre.my.test.robot.dto.user.UserInfo;

/**
 * Author:qiang.zeng on 2017/2/7.
 */
public interface UserInfoMapper {
    void save(UserInfo userInfo);
    UserInfo selectUserInfoByOpenid(String openid);
}
