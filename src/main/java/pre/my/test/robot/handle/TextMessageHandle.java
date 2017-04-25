package pre.my.test.robot.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pre.my.test.robot.dto.AccessToken;
import pre.my.test.robot.dto.autoresponse.AutoResponseMessage;
import pre.my.test.robot.dto.user.Group;
import pre.my.test.robot.dto.user.MsgBack;
import pre.my.test.robot.dto.user.UserInfo;
import pre.my.test.robot.service.IAutoResponseService;
import pre.my.test.robot.service.IMsgBackService;
import pre.my.test.robot.service.IUserInfoService;
import pre.my.test.robot.util.AccessTokenUtil;
import pre.my.test.robot.util.GroupUtil;
import pre.my.test.robot.util.MessageUtil;
import pre.my.test.robot.util.TuringAPIUtil;

import java.io.IOException;
import java.util.List;

/**
 * Author:qiang.zeng on 2017/3/27.
 */
@Component
public class TextMessageHandle {
    private static final Logger logger = LoggerFactory.getLogger(TextMessageHandle.class);
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IMsgBackService msgBackService;
    @Autowired
    private IAutoResponseService autoResponseService;


    //文本消息处理
    public String textMessageHandle(String fromUserName, String toUserName, String content) throws IOException {
        List<AutoResponseMessage> autoResponseMessages = autoResponseService.selectAll();
        for (AutoResponseMessage autoResponseMessage : autoResponseMessages) {
            String keyword = autoResponseMessage.getKeywordMsg();
            String[] keywords = keyword.split("，");
            for(String key:keywords){
                if (content.contains(key)) {
                    UserInfo userInfo = userInfoService.selectUserInfoByOpenid(fromUserName);
                    String resultContent = MessageUtil.initTextMessage(fromUserName, toUserName, autoResponseMessage.getResponseMsg());
                    MsgBack msgBack = new MsgBack();
                    msgBack.setUserid(userInfo.getUserid());
                    msgBack.setUserContent(content);
                    msgBack.setRobotContent(autoResponseMessage.getResponseMsg());
                    msgBackService.save(msgBack);
                    return resultContent;
                }
            }
        }
        if (content.equals("1")) {
            TuringAPIUtil.changeAddr("121.151558","31.169786");
        } else if (content.equals("2")) {
            return MessageUtil.initNewsMessage(fromUserName, toUserName);
        } else if (content.equals("3")) {
            return MessageUtil.initTextMessage(fromUserName, toUserName, "修改备注成功");
        } else if (content.equals("4")) {
            AccessToken token = AccessTokenUtil.getValidAccessToken();
        /*    //创建分组
            GroupUtil.createGroup(token.getToken(),"{\"group\":{\"name\":\"至尊VIP\"}}");*/
           /* //更新分组名
            int res = GroupUtil.updateName(token.getToken(),"{\"group\":{\"id\":100,\"name\":\"911战队成员\"}}");*/
            //{"openid":"o9eW3w7nm2-cYoao_qiXrtrUeQ9w","to_groupid":100}
            /*//移动用户分组
            GroupUtil.moveUser(token.getToken(),"{\"openid\":\"o9eW3w7nm2-cYoao_qiXrtrUeQ9w\",\"to_groupid\":100}");
            GroupUtil.moveUser(token.getToken(),"{\"openid\":\"o9eW3w5m36mEn4uH2QLvJQjZ-nxI\",\"to_groupid\":100}");*/
            /*//批量移动用户分组
            GroupUtil.batchMoveUser(token.getToken(), "{\"openid_list\":[\"o9eW3w8Dh3W0ba-FIehQEJ6d_Bq8\",\"o9eW3wzB_aULaLH0xOMOYlOJ0ETg\",\"o9eW3w23uu7Hf5yaeSFzcG0_ZToQ\"],\"to_groupid\":100}");*/
            //查询所有分组
            List<Group> groups = GroupUtil.queryAll(token.getToken());
            groups.size();
          /*  //删除用户分组
            int result3 = GroupUtil.deleteGroup(token.getToken(), "{\"group\":{\"id\":104}}");*/
           /* //查询用户所在分组
           String groupid = GroupUtil.queryUser(token.getToken(),"{\"openid\":\"o9eW3w7nm2-cYoao_qiXrtrUeQ9w\"}");*/
        } else if (content.equals("?") || content.equals("？")) {
            return MessageUtil.initTextMessage(fromUserName, toUserName, MessageUtil.firstMenu());
        }/* else if (content.contains("911")) {
            String mediaId = "3lIPHNZnAliHoT6AO9ZJEGZo9nUCFZt8M6w-7ixY2kFok9UCHyP80RcJgG0VDUil";
            return MessageUtil.initImageMessage(fromUserName, toUserName, mediaId);
        }*/ else {
            UserInfo userInfo = userInfoService.selectUserInfoByOpenid(fromUserName);
            String resultContent = TuringAPIUtil.getTuringResult(content);
            logger.debug("回复内容字节：" + String.valueOf(resultContent.getBytes("utf-8").length));
            logger.debug(userInfo.getNickname() + "输入内容：" + content);
            logger.debug("机器人回复：" + resultContent);
            MsgBack msgBack = new MsgBack();
            msgBack.setUserid(userInfo.getUserid());
            msgBack.setUserContent(content);
            msgBack.setRobotContent(resultContent);
            msgBackService.save(msgBack);
            return MessageUtil.initTextMessage(fromUserName, toUserName, resultContent);
        }

        return MessageUtil.initTextMessage(fromUserName, toUserName, "您发送的内容是：" + content);
    }
}
