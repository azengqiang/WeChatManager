package pre.my.test.robot.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pre.my.test.robot.dto.user.Group;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户分组工具类
 * Author:qiang.zeng on 2017/2/8.
 */
public class GroupUtil {
    /**
     * 创建分组
     *
     * @param token
     * @param group 如：{"group":{"name":"test"}}
     * @return
     */
    public static JSONObject createGroup(String token, String group) {
        String url = Constants.GROUP_CREATE_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = null;
        try {
            jsonObject = HttpConnectUtil.doPostStr(url, group);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 查询公众号所有用户分组
     *
     * @param token
     * @return 用户分组列表
     */
    public static List<Group> queryAll(String token) {
        List<Group> groups = new ArrayList<>();
        String url = Constants.GROUP_QUERY_ALL_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = null;
        try {
            jsonObject = HttpConnectUtil.doGetStr(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonObject != null) {
            JSONArray openidList = jsonObject.getJSONArray("groups");
            Group group = new Group();
            for (int i = 0; i < openidList.size(); i++) {
                JSONObject object = (JSONObject) openidList.get(i);
                group.setId(object.getString("id"));
                group.setName(object.getString("name"));
                group.setCount(object.getString("count"));
                groups.add(group);
            }
        }
        return groups;
    }

    /**
     * 查询用户所在分组
     *
     * @param token
     * @param openid 用户的openid
     * @return 用户所在分组id
     */
    public static String queryUser(String token, String openid) {
        String url = Constants.GROUP_QUERY_USER_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = null;
        try {
            jsonObject = HttpConnectUtil.doPostStr(url, openid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject.getString("groupid");
    }

    /**
     * 更新分组名
     *
     * @param token
     * @param group 如：{"group":{"id":108,"name":"test2_modify2"}}
     * @return 执行结果，成功：0，失败：40013
     */
    public static int updateName(String token, String group) {
        int result = 0;
        String url = Constants.GROUP_UPDATE_NAME_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = null;
        try {
            jsonObject = HttpConnectUtil.doPostStr(url, group);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonObject != null) {
            result = jsonObject.getInteger("errcode");
        }
        return result;
    }

    /**
     * 移动用户分组
     *
     * @param token
     * @param User  如：{"openid":"oDF3iYx0ro3_7jD4HFRDfrjdCM58","to_groupid":108}
     * @return 执行结果，成功：0，失败：40013
     */
    public static int moveUser(String token, String User) {
        int result = 0;
        String url = Constants.GROUP_MOVE_USER_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = null;
        try {
            jsonObject = HttpConnectUtil.doPostStr(url, User);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonObject != null) {
            result = jsonObject.getInteger("errcode");
        }
        return result;
    }

    /**
     * 批量移动用户分组
     *
     * @param token
     * @param batchUser 如：{"openid_list":["oDF3iYx0ro3_7jD4HFRDfrjdCM58","oDF3iY9FGSSRHom3B-0w5j4jlEyY"],"to_groupid":108}
     * @return 执行结果，成功：0，失败：40013
     */
    public static int batchMoveUser(String token, String batchUser) {
        int result = 0;
        String url = Constants.GROUP_BATCH_MOVE_USER_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = null;
        try {
            jsonObject = HttpConnectUtil.doPostStr(url, batchUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonObject != null) {
            result = jsonObject.getInteger("errcode");
        }
        return result;
    }

    /**
     * 删除用户分组，所有该分组内的用户自动进入默认分组
     *
     * @param token
     * @param id    分组id 如：{"group":{"id":108}}
     * @return 执行结果，成功：0，失败：40013
     */
    public static int deleteGroup(String token, String id) {
        int result = 0;
        String url = Constants.GROUP_DELETE_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = null;
        try {
            jsonObject = HttpConnectUtil.doPostStr(url, id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonObject != null) {
            result = jsonObject.getInteger("errcode");
        }
        return result;
    }
}
