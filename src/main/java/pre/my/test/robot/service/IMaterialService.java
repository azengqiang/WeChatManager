package pre.my.test.robot.service;

import pre.my.test.robot.dto.material.Material;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/3/27.
 */
public interface IMaterialService {
    void save(Material material);
    Material selectMaterialByMediaId(String meidaId);
    List<Material> selectAll();
}
