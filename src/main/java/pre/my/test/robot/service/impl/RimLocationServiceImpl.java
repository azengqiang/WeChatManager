package pre.my.test.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.my.test.robot.dto.location.RimLocation;
import pre.my.test.robot.mapper.RimLocationMapper;
import pre.my.test.robot.service.IRimLocationService;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/25.
 */
@Service
public class RimLocationServiceImpl implements IRimLocationService {
    @Autowired
    private RimLocationMapper mapper;

    @Override
    public List<RimLocation> select(RimLocation rimLocation) {
        return mapper.select(rimLocation);
    }

    @Override
    public void save(RimLocation rimLocation) {
        mapper.save(rimLocation);
    }

    @Override
    public void update(RimLocation rimLocation) {
        mapper.update(rimLocation);
    }
}
