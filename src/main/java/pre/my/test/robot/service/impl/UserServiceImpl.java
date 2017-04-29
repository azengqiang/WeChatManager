package pre.my.test.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.my.test.robot.dto.user.User;
import pre.my.test.robot.dto.user.UserInfo;
import pre.my.test.robot.mapper.UserMapper;
import pre.my.test.robot.service.IUserInfoService;
import pre.my.test.robot.service.IUserService;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/29.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User select(User user) {
        return userMapper.select(user);
    }

    @Override
    public void delete(User user) {
        userMapper.delete(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }
}
