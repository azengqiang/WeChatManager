package pre.my.test.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.my.test.robot.dto.user.Group;
import pre.my.test.robot.mapper.GroupMapper;
import pre.my.test.robot.service.IGroupService;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/17.
 */
@Service
public class GroupServiceImpl implements IGroupService {
    @Autowired
    private GroupMapper mapper;

    @Override
    public void save(Group group) {
        mapper.save(group);
    }

    @Override
    public void delete(Group group) {
        mapper.delete(group);
    }

    @Override
    public List<Group> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public Group selectByName(String name) {
        return mapper.selectByName(name);
    }

    @Override
    public Group select(Group group) {
        return mapper.select(group);
    }

    @Override
    public void updateName(Group group) {
         mapper.updateName(group);
    }

    @Override
    public void updateCount(Group group) {
        mapper.updateCount(group);
    }
}
