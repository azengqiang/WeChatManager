package pre.my.test.robot.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.my.test.robot.dto.autoresponse.AutoResponseMessage;
import pre.my.test.robot.mapper.AutoResponseMapper;
import pre.my.test.robot.service.IAutoResponseService;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/21.
 */
@Service
public class AutoResponseServiceImpl implements IAutoResponseService {
    @Autowired
    private AutoResponseMapper mapper;

    @Override
    public void save(AutoResponseMessage autoResponseMessage) {
        mapper.save(autoResponseMessage);
    }

    @Override
    public void delete(AutoResponseMessage autoResponseMessage) {
        mapper.delete(autoResponseMessage);
    }

    @Override
    public void update(AutoResponseMessage autoResponseMessage) {
        mapper.update(autoResponseMessage);
    }

    @Override
    public List<AutoResponseMessage> select(AutoResponseMessage autoResponseMessage) {
        return mapper.select(autoResponseMessage);
    }

    @Override
    public List<AutoResponseMessage> selectAll(Integer pageSize, Integer pageNumber) {
        pageNumber = pageNumber == null ? 1 : pageNumber;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNumber, pageSize);
        return mapper.selectAll();
    }

    @Override
    public List<AutoResponseMessage> selectAll() {
        return mapper.selectAll();
    }

}
