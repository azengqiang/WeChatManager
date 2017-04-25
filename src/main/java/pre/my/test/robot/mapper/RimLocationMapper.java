package pre.my.test.robot.mapper;

import pre.my.test.robot.dto.location.RimLocation;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/25.
 */
public interface RimLocationMapper {
    List<RimLocation> select(RimLocation rimLocation);
    void save(RimLocation rimLocation);
    void update(RimLocation rimLocation);
}
