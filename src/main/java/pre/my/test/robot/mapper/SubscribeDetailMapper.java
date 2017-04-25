package pre.my.test.robot.mapper;

import pre.my.test.robot.dto.user.SubscribeDetail;

/**
 * Author:qiang.zeng on 2017/4/25.
 */
public interface SubscribeDetailMapper {
    void delete(SubscribeDetail subscribeDetail);

    void save(SubscribeDetail subscribeDetail);

    void update(SubscribeDetail subscribeDetail);

    void select(SubscribeDetail subscribeDetail);
}
