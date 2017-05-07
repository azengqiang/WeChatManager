package pre.my.test.robot.mapper;

import pre.my.test.robot.dto.user.SubscribeAnalysis;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/25.
 */
public interface SubscribeDetailMapper {
    void delete(SubscribeAnalysis subscribeAnalysis);

    void save(SubscribeAnalysis subscribeAnalysis);

    void update(SubscribeAnalysis subscribeAnalysis);

    List<SubscribeAnalysis> select(SubscribeAnalysis subscribeAnalysis);

    List<SubscribeAnalysis> selectUserNum(SubscribeAnalysis subscribeAnalysis);
}
