package pre.my.test.robot.mapper;

import pre.my.test.robot.dto.menu.MenuDetail;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/27.
 */
public interface MenuDetailMapper {
    List<MenuDetail> selectAll();
    void save(MenuDetail menuDetail);
    void deleteAll();
}
