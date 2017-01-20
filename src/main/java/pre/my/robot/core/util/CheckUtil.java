package pre.my.robot.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 微信接口配置验证工具类
 * Author:qiang.zeng@hand-china.com on 2017/1/12.
 */
public class CheckUtil {
    private static String TOKEN = "swpu911";

    public static boolean checkSignature(String signature, String timestamp, String nonce) throws NoSuchAlgorithmException {
        String[] str = new String[]{TOKEN, timestamp, nonce};
        //排序
        Arrays.sort(str);
        StringBuffer buffer = new StringBuffer();
        //拼接字符串
        for (String s : str) {
            buffer.append(s);
        }
        //返回sha1加密后的字符串与signature的匹配情况
        return sha1(buffer.toString()).equals(signature);
    }

    //sha1加密
    public static String sha1(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(data.getBytes());
        StringBuffer buf = new StringBuffer();
        byte[] bits = md.digest();
        for (int i = 0; i < bits.length; i++) {
            int a = bits[i];
            if (a < 0) a += 256;
            if (a < 16) buf.append("0");
            buf.append(Integer.toHexString(a));
        }
        return buf.toString();
    }


}
