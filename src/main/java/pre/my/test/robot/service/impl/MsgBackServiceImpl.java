package pre.my.test.robot.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.my.test.robot.dto.user.MsgBack;
import pre.my.test.robot.dto.user.UserInfo;
import pre.my.test.robot.mapper.MsgBackMapper;
import pre.my.test.robot.service.IMsgBackService;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/2/10.
 */
@Service
public class MsgBackServiceImpl implements IMsgBackService {
    @Autowired
    private MsgBackMapper mapper;

    @Override
    public void save(MsgBack msgBack) {
        mapper.save(msgBack);
    }

    @Override
    public int selectCount() {
        return mapper.selectCount();
    }

    @Override
    public List<MsgBack> selectAllMsgBack(Integer pageSize, Integer pageNumber) {
        pageNumber = pageNumber == null ? 1 : pageNumber;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNumber, pageSize);
        return mapper.selectAllMsgBack();
    }
}
