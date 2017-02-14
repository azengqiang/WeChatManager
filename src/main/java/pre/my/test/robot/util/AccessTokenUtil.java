package pre.my.test.robot.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pre.my.test.robot.controller.CallBackWeiXin;
import pre.my.test.robot.dto.AccessToken;

import java.io.IOException;

/**
 * 使用缓存，保证accessToken长期有效
 * Author:qiang.zeng on 2017/2/8.
 */
public class AccessTokenUtil {
    private static AccessToken accessToken = null;
    private static final Logger logger = LoggerFactory.getLogger(CallBackWeiXin.class);
    /**
     * 使用缓存，使accessToken始终有效
     * @return 有效的accessToken
     * @throws IOException
     */
    public static AccessToken getValidAccessToken() throws IOException {
        if(null==accessToken){
            accessToken = getAccessToken();
            logger.debug("第一次获取accessToken:" + accessToken.getToken());
            Cache cache = new Cache();
            cache.setValue(accessToken.getToken());
            CacheManager.putCacheInfo("accessToken", cache,  7200*1000);
        }else{
            Cache cache = CacheManager.getCacheInfo("accessToken");
            if(cache.isExpired()){
                CacheManager.clearOnly("accessToken");
                accessToken = getAccessToken();
                logger.debug("accessToken失效重获取:" + accessToken.getToken());
                cache.setValue(accessToken.getToken());
                CacheManager.putCacheInfo("accessToken", cache, 7200*1000);
            }
        }
        logger.debug("获取的缓存accessToken:" +accessToken.getToken());
        return accessToken;
    }
    /**
     * 从微信获取accessToken
     * @return
     * @throws IOException
     */
    private static AccessToken getAccessToken()  {
        AccessToken accessToken = new AccessToken();
        String url = Constants.ACCESS_TOKEN_URL.replace("APPID", Constants.APP_ID).replace("APPSECRET", Constants.APP_SECRET);
        JSONObject jsonObject = null;
        try {
            jsonObject = HttpConnectUtil.doGetStr(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonObject != null) {
            accessToken.setToken(jsonObject.getString("access_token"));
            accessToken.setExpiresIn(jsonObject.getInteger("expires_in"));
        }
        return accessToken;
    }
}
