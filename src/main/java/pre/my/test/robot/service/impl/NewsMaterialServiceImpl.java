package pre.my.test.robot.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.my.test.robot.dto.material.NewsMaterial;
import pre.my.test.robot.mapper.NewsMaterialMapper;
import pre.my.test.robot.service.INewsMaterialService;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/28.
 */
@Service
public class NewsMaterialServiceImpl implements INewsMaterialService {
    @Autowired
    private NewsMaterialMapper mapper;

    @Override
    public void save(NewsMaterial newsMaterial) {
        mapper.save(newsMaterial);
    }

    @Override
    public void update(NewsMaterial newsMaterial) {
        mapper.update(newsMaterial);
    }

    @Override
    public void delete(NewsMaterial newsMaterial) {
        mapper.delete(newsMaterial);
    }

    @Override
    public List<NewsMaterial> select(Integer pageSize, Integer pageNumber, NewsMaterial newsMaterial) {
        pageNumber = pageNumber == null ? 1 : pageNumber;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNumber, pageSize);
        return mapper.select(newsMaterial);
    }

    @Override
    public NewsMaterial selectBy(NewsMaterial newsMaterial) {
        return mapper.selectBy(newsMaterial);
    }

    @Override
    public List<NewsMaterial> getSelectSize(NewsMaterial newsMaterial) {
        return mapper.select(newsMaterial);
    }
}
