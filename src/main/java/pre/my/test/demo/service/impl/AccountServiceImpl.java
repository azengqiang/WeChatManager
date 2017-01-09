package pre.my.test.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.my.test.demo.dto.User;
import pre.my.test.demo.mapper.UserMapper;
import pre.my.test.demo.service.IAccountService;

/**
 * Author:qiang.zeng@hand-china.com on 2017/1/6.
 */
@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private UserMapper mapper;
    @Override
    public User login(Long id) {
        return mapper.selectUserById(id);
    }
}
