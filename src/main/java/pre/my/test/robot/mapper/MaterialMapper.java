package pre.my.test.robot.mapper;

import pre.my.test.robot.dto.material.Material;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/2/7.
 */
public interface MaterialMapper {
    void save(Material material);

    Material selectMaterialByMediaId(String meidaId);

    List<Material> selectAll();

    void delete(Material material);

    void update(Material material);
}
