package pre.my.test.robot.service.impl;

/**
 * Author:qiang.zeng on 2017/4/25.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.my.test.robot.dto.user.SubscribeAnalysis;
import pre.my.test.robot.mapper.SubscribeDetailMapper;
import pre.my.test.robot.service.ISubscribeDetailService;

import java.util.List;

@Service
public class SubscribeDetailServiceImpl implements ISubscribeDetailService {
    @Autowired
    private SubscribeDetailMapper mapper;


    @Override
    public void delete(SubscribeAnalysis subscribeAnalysis) {
        mapper.delete(subscribeAnalysis);
    }

    @Override
    public void save(SubscribeAnalysis subscribeAnalysis) {
        mapper.save(subscribeAnalysis);
    }

    @Override
    public void update(SubscribeAnalysis subscribeAnalysis) {
        mapper.update(subscribeAnalysis);
    }

    @Override
    public List<SubscribeAnalysis> select(SubscribeAnalysis subscribeAnalysis) {
       return mapper.select(subscribeAnalysis);
    }

    @Override
    public List<SubscribeAnalysis> selectUserNum(SubscribeAnalysis subscribeAnalysis) {
        return mapper.selectUserNum(subscribeAnalysis);
    }
}
