package pre.my.test.robot.mapper;

import pre.my.test.robot.dto.user.UserInfo;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/2/7.
 */
public interface UserInfoMapper {
    void save(UserInfo userInfo);

    UserInfo selectUserInfoByOpenid(String openid);

    List<UserInfo> selectAll();

    void updateGroupByOpenId(UserInfo userInfo);

    void updateRemarkByOpenId(UserInfo userInfo);

    List<UserInfo> select(UserInfo userInfo);

    void delete(UserInfo userInfo);

    void update(UserInfo userInfo);
}
