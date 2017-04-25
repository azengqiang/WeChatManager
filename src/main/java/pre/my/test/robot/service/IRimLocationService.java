package pre.my.test.robot.service;

import pre.my.test.robot.dto.location.RimLocation;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/25.
 */
public interface IRimLocationService {
    List<RimLocation> select(RimLocation rimLocation);
    void save(RimLocation rimLocation);
    void update(RimLocation rimLocation);
}
