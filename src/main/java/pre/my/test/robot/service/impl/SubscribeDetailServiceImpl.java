package pre.my.test.robot.service.impl;

/**
 * Author:qiang.zeng on 2017/4/25.
 */

import com.sun.org.apache.bcel.internal.generic.ISUB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.my.test.robot.dto.user.SubscribeDetail;
import pre.my.test.robot.mapper.SubscribeDetailMapper;
import pre.my.test.robot.service.ISubscribeDetailService;

@Service
public class SubscribeDetailServiceImpl implements ISubscribeDetailService {
    @Autowired
    private SubscribeDetailMapper mapper;


    @Override
    public void delete(SubscribeDetail subscribeDetail) {
        mapper.delete(subscribeDetail);
    }

    @Override
    public void save(SubscribeDetail subscribeDetail) {
        mapper.save(subscribeDetail);
    }

    @Override
    public void update(SubscribeDetail subscribeDetail) {
        mapper.update(subscribeDetail);
    }

    @Override
    public void select(SubscribeDetail subscribeDetail) {
        mapper.select(subscribeDetail);
    }
}
