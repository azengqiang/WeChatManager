import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import pre.my.test.robot.dto.user.User;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;
import java.util.jar.JarEntry;

/**
 * Author:qiang.zeng@hand-china.com on 2017/1/12.
 */
public class Test {

    @org.junit.Test
    public void test7() {
        String params = "userName;status;userId;";
        System.out.println( Arrays.asList(StringUtils.split(params,";")));
    }

    @org.junit.Test
    public void test6(){
        Long a = 1L;
        Long b = 1L;

        if(a==b){
            System.out.println(a==b);
        }
        if(a.equals(b)){
            System.out.println(a.equals(b));
        }


    }

    @org.junit.Test
    public void test5() {
        Map accessData = new HashMap();
        accessData.put("ad", "123");
        String ad = (String)accessData.get("aa");
        System.out.println(ad);

       /* List<Map> btnMap = new ArrayList<>();
        List<Map> formMap = new ArrayList<>();
        List<Map> gridMap = new ArrayList<>();
        accessData.put("buttons", btnMap);
        accessData.put("grid", formMap);
        accessData.put("form", gridMap);
        Map map = new HashMap();
        map.put("id","addBtn");
        btnMap.add(map);
        Map map2 = new HashMap();
        map2.put("id","saveBtn");
        btnMap.add(map2);
        Map map3 = new HashMap();
        map3.put("id","delBtn");
        btnMap.add(map3);
       Map map4 = new HashMap();
       map4.put("id","formId");
*/


    }

    @org.junit.Test
    public void test4() {

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        users.add(user);
        User user2 = new User();
        user2.setId(1L);
        users.add(user2);
        if (true) {
            users.remove(user2);
        }
        System.out.println(users);
    }

    @org.junit.Test
    public void test3() {
        Long[] id1 = null;
        Long[] id2 = {2L, 3L, 1L};
        Set<Long> userFunctions = new HashSet<>();
        if (ArrayUtils.isNotEmpty(id1)) {
            userFunctions.addAll(Arrays.asList(id1));
        }
        userFunctions.addAll(Arrays.asList(id2));

        System.out.println(userFunctions);
    }

    @org.junit.Test
    public void test2() {
        List<Long> a = new ArrayList<>();
        a.add(1L);
        a.add(2L);
        a.add(3L);
        a.add(4L);
        Long[] id2 = {2L, 3L, 1L};
        List<Long> b  = Arrays.asList(id2);
     /*   List<Long> b = new ArrayList<>();
        b.add(4L);
        b.add(5L);
        b.add(6L);
        b.add(7L);*/
        a.removeAll(b);
        a.addAll(b);
        System.out.println(a);
    }

    @org.junit.Test
    public void test1() {
      /*  User user = new User();
        user.setId(1L);
        user.setUserName("ssd");
        user.setPassword("aad");
        String u = JSON.toJSONString(user);
        System.out.println(u);*/
        JSONObject accessData = new JSONObject();
        JSONArray buttonsArr = new JSONArray();
        accessData.put("buttons", buttonsArr);
        JSONArray formArr = new JSONArray();
        accessData.put("form", formArr);
        JSONArray gridArr = new JSONArray();
        accessData.put("grid", gridArr);

        buildArray("id", "save_btn", buttonsArr);
        buildArray("id", "delete_btn", buttonsArr);


        JSONObject form = new JSONObject();
        form.put("id", "formId");
        JSONArray fields = new JSONArray();
        JSONArray buttons = new JSONArray();
        form.put("fields", fields);
        form.put("buttons", buttons);
        formArr.add(form);
        buildArray("id", "fieldName", fields);
        buildArray("id", "buttonId", buttons);


        JSONObject grid = new JSONObject();
        grid.put("id", "girdId");
        JSONArray columns = new JSONArray();
        JSONArray buttons1 = new JSONArray();
        JSONArray columnButtons = new JSONArray();
        grid.put("columns", columns);
        grid.put("buttons", buttons1);
        grid.put("columnButtons", columnButtons);
        gridArr.add(grid);

        buildArray("name", "colName", columns);
        buildArray("id", "btnId", buttons1);
        buildArray("buttonType", "add", columnButtons);

        System.out.println(accessData.toJSONString());
       /* List<String> all = new ArrayList<>();
        String[] a = null;
        all.addAll(Arrays.asList(a));
        for(String s:all){
                System.out.print("123");
        }*/
    }


    private void buildArray(String fieldName, String value, JSONArray array) {
        JSONObject object = new JSONObject();
        object.put(fieldName, value);
        array.add(object);
    }


    @org.junit.Test
    public void test() throws UnknownHostException, SocketException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("本机IP:" + inetAddress.getHostAddress().toString());
        System.out.println("本机名称:" + inetAddress.getHostName().toString());
      /*  Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            System.out.println(netInterface.getName());
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = (InetAddress) addresses.nextElement();
                if (ip.isSiteLocalAddress()) {
                    System.out.println("本机的IP = " + ip.getHostAddress());
                }
            }
        }*/
        Enumeration<NetworkInterface> netInterfaces = null;
        netInterfaces = NetworkInterface.getNetworkInterfaces();
        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> ips = ni.getInetAddresses();
            while (ips.hasMoreElements()) {
                InetAddress ip = ips.nextElement();
                if (ip.isSiteLocalAddress()) {
                    System.out.println("本机的IP = " + ip.getHostAddress());
                }
            }
        }
         /*   String str = "id = ? and name = ?";
        String[] ss = str.trim().split(" ");
        for(int i = 0; i < ss.length; i++){
            if(ss[i].equals("=")){
                System.out.print(ss[i-1] + " ");

            }
        }*/
    }
}
