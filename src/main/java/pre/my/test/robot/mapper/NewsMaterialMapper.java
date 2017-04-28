package pre.my.test.robot.mapper;

import pre.my.test.robot.dto.material.NewsMaterial;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/28.
 */
public interface NewsMaterialMapper {
    void save(NewsMaterial newsMaterial);
    void update(NewsMaterial newsMaterial);
    void delete(NewsMaterial newsMaterial);
    List<NewsMaterial> select(NewsMaterial newsMaterial);
}
