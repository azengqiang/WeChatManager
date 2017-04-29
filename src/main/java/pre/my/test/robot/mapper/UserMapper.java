package pre.my.test.robot.mapper;


import pre.my.test.robot.dto.user.User;

/**
 * Created by win on 2016/11/16.
 */
public interface UserMapper {
    User select(User user);

    void delete(User user);

    void update(User user);

    void save(User user);
}
