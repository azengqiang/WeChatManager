import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pre.my.test.robot.util.TimeStamp2Date;

/**
 * Author:qiang.zeng@hand-china.com on 2017/1/12.
 */
public class Test {
    @org.junit.Test
    public void test(){
       /* String str = "id = ? and name = ?";
        String[] ss = str.trim().split(" ");
        for(int i = 0; i < ss.length; i++){
            if(ss[i].equals("=")){
                System.out.print(ss[i-1] + " ");

            }
        }*/
      /* String date =  TimeStamp2Date.TimeStamp2Date("1484813548", "yyyy-MM-dd HH:mm:ss");
        System.out.println(date);*/
        JSONArray array = (JSONArray)JSON.parse("[{\"equipmentId\":\"4\"},{\"equipmentId\":\"5\"}]");
        for(int i=0;i<array.size();i++){
            JSONObject jsonObject =(JSONObject)array.get(i);
            String equipmentId = jsonObject.getString("equipmentId");

        }
        System.out.print(array);
    }
}
