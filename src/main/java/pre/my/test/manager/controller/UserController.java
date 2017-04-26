package pre.my.test.manager.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pre.my.test.robot.dto.user.*;
import pre.my.test.robot.service.IGroupService;
import pre.my.test.robot.service.IUserInfoService;
import pre.my.test.robot.util.AccessTokenUtil;
import pre.my.test.robot.util.GroupUtil;
import pre.my.test.robot.util.TimeStamp2Date;
import pre.my.test.robot.util.UserManagerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:qiang.zeng on 2017/2/15.
 */
@Controller
@RequestMapping(value = "/admin")
public class UserController {
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IGroupService groupService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    @ResponseBody
    public void userInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //得到客户端传递的页码和每页记录数，并转换成int类型
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        List<UserInfo> userInfos = userInfoService.selectAll(pageSize, pageNumber);
        int total = userInfoService.selectAll().size();
        response.setCharacterEncoding("UTF-8"); //设置编码格式
        response.setContentType("text/html");   //设置数据格式
        PrintWriter out = response.getWriter(); //获取写入对象

        String json = "{\"total\":" + total + ",\"rows\":" + JSON.toJSON(userInfos) + "}";
        out.print(json);
        out.flush();
    }

    @RequestMapping(value = "/groupUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public void groupUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //得到客户端传递的页码和每页记录数，并转换成int类型
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        String searchText = request.getParameter("searchText");
        String groupid = request.getParameter("groupid");

        List<UserInfo> userInfos;
        int total;
        UserInfo userInfo = new UserInfo();
        if (StringUtils.isNotEmpty(searchText)) {
            userInfo.setNickname(searchText);
        }
        if (StringUtils.isNotEmpty(groupid) && groupid.equals("-1")) {
            total = userInfoService.getSelectSize(null).size();
            userInfos = userInfoService.select(pageSize, pageNumber, userInfo);
        } else {
            userInfo.setGroupid(groupid);
            total = userInfoService.getSelectSize(userInfo).size();
            userInfos = userInfoService.select(pageSize, pageNumber, userInfo);
        }

        response.setCharacterEncoding("UTF-8"); //设置编码格式
        response.setContentType("text/html");   //设置数据格式
        PrintWriter out = response.getWriter(); //获取写入对象

        String json = "{\"total\":" + total + ",\"rows\":" + JSON.toJSON(changeUserInfo(userInfos)) + "}";
        out.print(json);
        out.flush();
    }

    /**
     * 转换时间戳、性别、用户组
     *
     * @param userInfos
     * @return
     */
    private List<UserInfo> changeUserInfo(List<UserInfo> userInfos) {
        for (UserInfo userInfo : userInfos) {
            String originTime = userInfo.getSubscribe_time();
            String originSex = userInfo.getSex();
            userInfo.setSex(changeSex(originSex));
            userInfo.setGroupName(changeGroup(userInfo.getGroupid()));
            userInfo.setSubscribe_time(TimeStamp2Date.TimeStamp2Date(originTime, "yyyy-MM-dd HH:mm:ss"));
        }
        return userInfos;
    }

    /**
     * 转换用户组
     *
     * @param groupId
     * @return
     */
    private String changeGroup(String groupId) {
        Group group = groupService.select(new Group(groupId, null, null));
        return group.getName();
    }

    /**
     * 转换性别
     *
     * @param sex 0|1|2
     * @return 未知|男|女
     */
    private String changeSex(String sex) {
        String sexName = "";
        if (sex.contains("0")) {
            sexName = "未知";
        } else if (sex.contains("1")) {
            sexName = "男";
        } else if (sex.contains("2")) {
            sexName = "女";
        }
        return sexName;
    }

    @RequestMapping(value = "/toUserInfo", method = RequestMethod.GET)
    public String toUserInfo(HttpServletRequest request, HttpServletResponse response) {
        return "user/userinfo2";
    }

    @RequestMapping(value = "/toGroup", method = RequestMethod.GET)
    public String toGroup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //从微信获取用户分组 存入数据库
       //List<Group> groups = GroupUtil.queryAll(AccessTokenUtil.getValidAccessToken().getToken());
       /* for(Group group:groups){
            Group group1 = new Group(group.getId(),group.getName(),group.getCount());
            groupService.save(group1);
        }*/
        //数据库查询所有分组
        List<Group> groups = groupService.selectAll();

        int userCount = 0;
        List<GroupTree> nodes = new ArrayList<>();
        for (Group group : groups) {
            if ("未分组".equals(group.getName()) || "黑名单".equals(group.getName()) || "星标组".equals(group.getName())) {
                GroupTree subNode = new GroupTree(group.getName(), new String[]{group.getCount(), "默认分组"}, null);
                nodes.add(subNode);
            } else {
                GroupTree subNode = new GroupTree(group.getName(), new String[]{group.getCount()}, null);
                nodes.add(subNode);
            }
            //总分组人数
            userCount = userCount + Integer.valueOf(group.getCount());
        }
        //构造分组树json数据 传递给前台
        GroupTree groupTree = new GroupTree("全部分组", new String[]{String.valueOf(userCount)}, nodes.toArray(new GroupTree[nodes.size()]));
        String json = "[" + JSONObject.toJSONString(groupTree) + "]";
        String groupsJson = JSONObject.toJSONString(groups);
        request.setAttribute("groupTreeJson", json);
        request.setAttribute("groups", groups);
        request.setAttribute("groupsJson", groupsJson);
        logger.debug("groupTree json data:" + json);
        logger.debug("groups json data:" + groupsJson);

        return "user/group";
    }

    @RequestMapping(value = "/createGroup", method = RequestMethod.POST)
    public String addGroup(HttpServletRequest request) throws IOException {
        String groupName = request.getParameter("groupName");
        //构造新建分组json数据
        GroupOperate groupOperateData = new GroupOperate();
        groupOperateData.setGroup(new Group(null, groupName, null));
        String group = JSON.toJSONString(groupOperateData);
        logger.debug("create group data: " + group);
        //调用微信接口 新建分组
        JSONObject jsonObject = GroupUtil.createGroup(AccessTokenUtil.getValidAccessToken().getToken(), group);
        //数据库存入
        GroupOperate groupOperate = JSON.parseObject(jsonObject.toJSONString(), GroupOperate.class);
        groupOperate.getGroup().setCount("0");
        groupService.save(groupOperate.getGroup());
        logger.debug("create group: " + JSON.toJSONString(groupOperate));

        return "redirect:toGroup";
    }

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
    public String deleteGroup(HttpServletRequest request, @RequestBody final List<Group> groups) throws IOException {
        for (Group group : groups) {
            //查询分组Id
            Group group1 = groupService.selectByName(group.getName());
            String deleteParam = JSON.toJSONString(new GroupOperate(group1));
            logger.debug("删除分组:" + deleteParam);
            //调用微信接口 删除分组
            int result = GroupUtil.deleteGroup(AccessTokenUtil.getValidAccessToken().getToken(), deleteParam);
            if (0 == result) {
                //数据库删除
                groupService.delete(group1);
                logger.debug("分组(" + group.getName() + ")删除成功");
            } else {
                logger.debug("分组(" + group.getName() + ")删除失败");
            }
        }
        resizeGroup();
        return "redirect:toGroup";
    }

    @RequestMapping(value = "/updateGroup", method = RequestMethod.POST)
    public String updateGroup(HttpServletRequest request) throws IOException {
        String newGroupName = request.getParameter("newGroupName");
        String originGroupName = request.getParameter("originGroupName");
        //构造修改分组名json数据
        Group group = groupService.selectByName(originGroupName);
        group.setName(newGroupName);
        GroupOperate groupOperate = new GroupOperate(group);
        logger.debug(JSON.toJSONString(groupOperate));
        //调用微信接口 修改分组名
        int result = GroupUtil.updateName(AccessTokenUtil.getValidAccessToken().getToken(), JSON.toJSONString(groupOperate));
        if (0 == result) {
            //数据库更新
            groupService.updateName(group);
            logger.debug("分组(" + originGroupName + ")修改名称成功 新分组名：" + newGroupName);
        } else {
            logger.debug("分组(" + originGroupName + ")修改名称失败");
        }
        return "redirect:toGroup";
    }

    /**
     * 移动用户分组 用户(分组)更新 分组(数量)更新
     * todo 需要事务支持
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/moveUser", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED)
    public String moveUser(HttpServletRequest request) throws IOException {
        String newGroupId = request.getParameter("newGroupId");
        String originUserGroupName = request.getParameter("originUserGroupName");
        String groupOpenId = request.getParameter("groupOpenId");
        MoveGroup moveGroup = new MoveGroup();
        moveGroup.setOpenid(groupOpenId);
        moveGroup.setTo_groupid(newGroupId);

        int result = GroupUtil.moveUser(AccessTokenUtil.getValidAccessToken().getToken(), JSON.toJSONString(moveGroup));
        if (0 == result) {
            //用户(分组)更新
            UserInfo userInfo = new UserInfo();
            userInfo.setGroupid(newGroupId);
            userInfo.setOpenid(groupOpenId);
            userInfoService.updateGroupByOpenId(userInfo);
            //分组(数量)更新
           /* //原分组数量减一
            Group originGroup = groupService.select(new Group(null, originUserGroupName, null));
            String originCount = String.valueOf(Integer.valueOf(originGroup.getCount()) - 1);
            originGroup.setCount(originCount);
            groupService.updateCount(originGroup);
            //新分组数量加一
            Group nowGroup = groupService.select(new Group(newGroupId, null, null));
            String nowCount = String.valueOf(Integer.valueOf(nowGroup.getCount()) + 1);
            nowGroup.setCount(nowCount);
            groupService.updateCount(nowGroup);*/
            resizeGroup();
            logger.debug("移动分组成功");
        } else {
            logger.debug("移动分组失败");
        }
        return "redirect:toGroup";
    }

    private void resizeGroup() throws IOException {
        List<Group> groups = GroupUtil.queryAll(AccessTokenUtil.getValidAccessToken().getToken());
        for (Group group : groups) {
            groupService.updateCount(group);
        }
        logger.debug("分组数量发送变化");
    }

    @RequestMapping(value = "/updateRemark", method = RequestMethod.POST)
    public String updateRemark(HttpServletRequest request) throws IOException {
        String newRemark = request.getParameter("newRemark");
        String remarkOpenId = request.getParameter("remarkOpenId");
        int result = UserManagerUtil.setRemark(AccessTokenUtil.getValidAccessToken().getToken(), new Remark(remarkOpenId, newRemark));
        if (0 == result) {
            UserInfo userInfo = new UserInfo();
            userInfo.setRemark(newRemark);
            userInfo.setOpenid(remarkOpenId);
            userInfoService.updateRemarkByOpenId(userInfo);
        }
        return "redirect:toGroup";
    }
    /*@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public String toIndex(HttpServletRequest request, HttpServletResponse response) {
        List<UserInfo> userInfos = userInfoService.selectAll();
        HttpSession session = request.getSession();
        request.setAttribute("userInfos", userInfos);
        return "user/userinfo";
    }*/
}
