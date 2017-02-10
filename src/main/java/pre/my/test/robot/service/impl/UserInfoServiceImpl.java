package pre.my.test.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.my.test.robot.dto.user.UserInfo;
import pre.my.test.robot.mapper.UserInfoMapper;
import pre.my.test.robot.service.IUserInfoService;

/**
 * Author:qiang.zeng on 2017/2/7.
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private UserInfoMapper mapper;

    @Override
    public void save(UserInfo userInfo) {
        mapper.save(userInfo);
    }

    @Override
    public UserInfo selectUserInfoByOpenid(String openid) {
        return mapper.selectUserInfoByOpenid(openid);
    }
}
