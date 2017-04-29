package pre.my.test.robot.service;

import pre.my.test.robot.dto.material.NewsMaterial;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/28.
 */
public interface INewsMaterialService {
    void save(NewsMaterial newsMaterial);

    void update(NewsMaterial newsMaterial);

    void delete(NewsMaterial newsMaterial);

    List<NewsMaterial> select(Integer pageSize, Integer pageNumber, NewsMaterial newsMaterial);

    NewsMaterial selectBy(NewsMaterial newsMaterial);

    List<NewsMaterial> getSelectSize(NewsMaterial newsMaterial);

}
