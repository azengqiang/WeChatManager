package pre.my.test.robot.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Author:qiang.zeng on 2017/1/19.
 */
@Deprecated
public class PostMenu {
    public void postMenu(String urlPath, String menuJson) throws IOException {
        URL url = new URL(urlPath);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        http.setDoOutput(true);
        http.setDoInput(true);
        System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
        System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
        http.connect();
        OutputStream os = http.getOutputStream();
        os.write(menuJson.getBytes("UTF-8"));//传入参数
        os.flush();
        os.close();
        InputStream is = http.getInputStream();
        int size = is.available();
        byte[] jsonBytes = new byte[size];
        is.read(jsonBytes);
        String message = new String(jsonBytes, "UTF-8");
        System.out.println(message);

    }
}
