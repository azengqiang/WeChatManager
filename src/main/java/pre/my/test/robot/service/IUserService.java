package pre.my.test.robot.service;

import pre.my.test.robot.dto.user.User;

/**
 * Author:qiang.zeng on 2017/4/29.
 */
public interface IUserService {
    User select(User user);

    void delete(User user);

    void update(User user);

    void save(User user);
}
