package pre.my.test.robot.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.my.test.robot.dto.user.UserInfo;
import pre.my.test.robot.mapper.UserInfoMapper;
import pre.my.test.robot.service.IUserInfoService;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/2/7.
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    protected UserInfoMapper mapper;

    @Override
    public void save(UserInfo userInfo) {
        mapper.save(userInfo);
    }

    @Override
    public void save(List<UserInfo> userInfos) {
        for (UserInfo userInfo : userInfos) {
            mapper.save(userInfo);
        }
    }

    @Override
    public UserInfo selectUserInfoByOpenid(String openid) {
        return mapper.selectUserInfoByOpenid(openid);
    }

    @Override
    public List<UserInfo> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public List<UserInfo> selectAll(Integer pageSize, Integer pageNumber) {
        pageNumber = pageNumber == null ? 1 : pageNumber;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNumber, pageSize);
        return mapper.selectAll();
    }

    @Override
    public void updateGroupByOpenId(UserInfo userInfo) {
        mapper.updateGroupByOpenId(userInfo);
    }

    @Override
    public void updateRemarkByOpenId(UserInfo userInfo) {
        mapper.updateRemarkByOpenId(userInfo);
    }

    @Override
    public List<UserInfo> select(Integer pageSize, Integer pageNumber, UserInfo userInfo) {
        pageNumber = pageNumber == null ? 1 : pageNumber;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNumber, pageSize);
        return mapper.select(userInfo);
    }

    @Override
    public List<UserInfo> getSelectSize(UserInfo userInfo) {
        List<UserInfo> userInfos = mapper.select(userInfo);
        return userInfos;
    }

    @Override
    public void delete(UserInfo userInfo) {
        mapper.delete(userInfo);
    }
}
