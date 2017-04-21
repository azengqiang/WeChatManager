package pre.my.test.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.my.test.robot.dto.autoresponse.AutoResponseMessage;
import pre.my.test.robot.mapper.AutoResponseMapper;
import pre.my.test.robot.service.AutoResponseService;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/21.
 */
@Service
public class AutoResponseServiceImpl implements AutoResponseService {
    @Autowired
    private AutoResponseMapper mapper;

    @Override
    public void save(AutoResponseMessage autoResponseMessage) {
        mapper.save(autoResponseMessage);
    }

    @Override
    public void delete(AutoResponseMessage autoResponseMessage) {
        mapper.delete(autoResponseMessage);
    }

    @Override
    public List<AutoResponseMessage> select(AutoResponseMessage autoResponseMessage) {
        return mapper.select(autoResponseMessage);
    }

}
