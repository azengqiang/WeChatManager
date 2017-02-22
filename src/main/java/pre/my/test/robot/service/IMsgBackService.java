package pre.my.test.robot.service;

import pre.my.test.robot.dto.user.MsgBack;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/2/10.
 */
public interface IMsgBackService {
    /**
     * 保存机器人聊天信息
     * @param msgBack
     */
    void save(MsgBack msgBack);

    /**
     * 机器人聊天信息数
     * @return
     */
    int selectCount();

    /**
     * 查询所有聊天记录
     * @param pageSize 分页大小
     * @param pageNumber 第几页
     * @return
     */
    List<MsgBack> selectAllMsgBack(Integer  pageSize,Integer  pageNumber);
}
