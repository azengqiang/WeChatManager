package pre.my.test.robot.service;

import pre.my.test.robot.dto.menu.MenuDetail;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/5/4.
 */
public interface IMenuDetailService {
    List<MenuDetail> selectAll();
    void save(MenuDetail menuDetail);
    void deleteAll();
}
