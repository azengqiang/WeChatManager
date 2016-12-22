package pre.my.test.demo.mapper;


import pre.my.test.demo.dto.User;

/**
 * Created by win on 2016/11/16.
 */
public interface UserMapper {
    public User  selectUserById(Long id);
}
