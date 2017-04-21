package pre.my.test.robot.service;

import pre.my.test.robot.dto.user.UserInfo;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/2/7.
 */
public interface IUserInfoService {
    /**
     * 保存单个用户信息
     *
     * @param userInfo
     */
    void save(UserInfo userInfo);

    /**
     * 保存多个用户信息
     *
     * @param userInfos 用户信息列表
     */
    void save(List<UserInfo> userInfos);

    /**
     * 查找单个用户信息
     *
     * @param openid
     * @return 用户信息
     */
    UserInfo selectUserInfoByOpenid(String openid);

    /**
     * 查询所有用户信息
     *
     * @return 用户信息列表
     */
    List<UserInfo> selectAll();

    /**
     * 查询所有用户信息
     *
     * @param pageSize   分页大小
     * @param pageNumber 第几页
     * @return 用户信息列表
     */
    List<UserInfo> selectAll(Integer pageSize, Integer pageNumber);

    void updateGroupByOpenId(UserInfo userInfo);

    void updateRemarkByOpenId(UserInfo userInfo);

    List<UserInfo> select(Integer pageSize, Integer pageNumber, UserInfo userInfo);

    List<UserInfo> getSelectSize(UserInfo userInfo);

    void delete(UserInfo userInfo);
}
