package pre.my.test.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.my.test.robot.dto.menu.MenuAnalysis;
import pre.my.test.robot.mapper.MaterialMapper;
import pre.my.test.robot.mapper.MenuAnalysisMapper;
import pre.my.test.robot.service.IMenuAnalysisService;

/**
 * Author:qiang.zeng on 2017/4/27.
 */
@Service
public class MenuAnalysisServiceImpl implements IMenuAnalysisService {
    @Autowired
    private MenuAnalysisMapper mapper;

    @Override
    public void select(MenuAnalysis menuAnalysis) {
        mapper.select(menuAnalysis);
    }

    @Override
    public void save(MenuAnalysis menuAnalysis) {
        mapper.save(menuAnalysis);
    }
}
