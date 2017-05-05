package pre.my.test.robot.mapper;

import pre.my.test.robot.dto.menu.MenuAnalysis;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/27.
 */
public interface MenuAnalysisMapper {
    List<MenuAnalysis> select(MenuAnalysis menuAnalysis);
    void save(MenuAnalysis menuAnalysis);
    List<MenuAnalysis> selectUserNum(MenuAnalysis menuAnalysis);
}
