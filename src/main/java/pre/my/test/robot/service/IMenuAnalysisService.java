package pre.my.test.robot.service;

import pre.my.test.robot.dto.menu.MenuAnalysis;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/27.
 */
public interface IMenuAnalysisService {
    List<MenuAnalysis> select(MenuAnalysis menuAnalysis);
    void save(MenuAnalysis menuAnalysis);
}
