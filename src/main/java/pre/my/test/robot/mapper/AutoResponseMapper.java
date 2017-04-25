package pre.my.test.robot.mapper;

import pre.my.test.robot.dto.autoresponse.AutoResponseMessage;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/21.
 */
public interface AutoResponseMapper {
    void save(AutoResponseMessage autoResponseMessage);

    void delete(AutoResponseMessage autoResponseMessage);

    void update(AutoResponseMessage autoResponseMessage);

    List<AutoResponseMessage> select(AutoResponseMessage autoResponseMessage);

    List<AutoResponseMessage> selectAll();
}
