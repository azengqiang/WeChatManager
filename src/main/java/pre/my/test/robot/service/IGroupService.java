package pre.my.test.robot.service;

import pre.my.test.robot.dto.user.Group;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/17.
 */
public interface IGroupService {
    void save(Group group);
    void delete(Group group);
    List<Group> selectAll();
    Group selectByName(String name);
    Group select(Group group);
    void updateName(Group group);
    void updateCount(Group group);
}
