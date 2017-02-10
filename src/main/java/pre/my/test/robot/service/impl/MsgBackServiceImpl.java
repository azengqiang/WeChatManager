package pre.my.test.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.my.test.robot.dto.user.MsgBack;
import pre.my.test.robot.mapper.MsgBackMapper;
import pre.my.test.robot.service.IMsgBackService;

/**
 * Author:qiang.zeng on 2017/2/10.
 */
@Service
public class MsgBackServiceImpl implements IMsgBackService {
    @Autowired
    private MsgBackMapper mapper;

    @Override
    public void save(MsgBack msgBack) {
        mapper.save(msgBack);
    }
}
