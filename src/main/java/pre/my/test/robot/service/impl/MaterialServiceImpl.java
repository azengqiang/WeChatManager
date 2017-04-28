package pre.my.test.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.my.test.robot.dto.material.Material;
import pre.my.test.robot.mapper.MaterialMapper;
import pre.my.test.robot.mapper.MsgBackMapper;
import pre.my.test.robot.service.IMaterialService;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/3/27.
 */
@Service
public class MaterialServiceImpl implements IMaterialService {
    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public void save(Material material) {
        materialMapper.save(material);
    }

    @Override
    public Material selectMaterialByMediaId(String meidaId) {
        return materialMapper.selectMaterialByMediaId(meidaId);
    }

    @Override
    public List<Material> selectAll() {
        return materialMapper.selectAll();
    }

    @Override
    public void delete(Material material) {
        materialMapper.delete(material);
    }

    @Override
    public void update(Material material) {
        materialMapper.update(material);
    }
}
