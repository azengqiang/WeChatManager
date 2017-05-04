package pre.my.test.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.my.test.robot.dto.menu.MenuAnalysis;
import pre.my.test.robot.dto.menu.MenuDetail;
import pre.my.test.robot.mapper.MenuAnalysisMapper;
import pre.my.test.robot.mapper.MenuDetailMapper;
import pre.my.test.robot.service.IMenuAnalysisService;
import pre.my.test.robot.service.IMenuDetailService;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/27.
 */
@Service
public class MenuDetailServiceImpl implements IMenuDetailService {
    @Autowired
    private MenuDetailMapper mapper;


    @Override
    public List<MenuDetail> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void save(MenuDetail menuDetail) {
        mapper.save(menuDetail);
    }

    @Override
    public void deleteAll() {
        mapper.deleteAll();
    }
}
