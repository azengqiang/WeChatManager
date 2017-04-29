package pre.my.test.robot.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pre.my.test.robot.dto.user.WebPageUserInfo;
import pre.my.test.robot.util.Constants;
import pre.my.test.robot.util.WebPageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Author:qiang.zeng@hand-china.com on 2017/1/4.
 */
@Controller
@RequestMapping(value = "/demo")
public class AccessController {
    private static final Logger logger = LoggerFactory.getLogger(AccessController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String toIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = request.getRequestURL().toString();
        String code = request.getParameter("code");
        if (code != null) {
            JSONObject jsonObject = WebPageUtil.getAccessToken(Constants.APP_ID, Constants.APP_SECRET, code);
            String accessToken = jsonObject.getString("access_token");
            String openid = jsonObject.getString("openid");
            WebPageUserInfo userInfo = WebPageUtil.getUserInfo(accessToken, openid);
            HttpSession session = request.getSession();
            session.setAttribute("url", url);
            request.setAttribute("user", userInfo.getNickname());
        } else {
            return "forward:access";
        }

        return "user/userinfo";
    }

    @RequestMapping(value = "/access", method = RequestMethod.GET)
    public void getAccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = (String) request.getSession().getAttribute("url");
        //授权链接
        String accessUrl = WebPageUtil.getcode(Constants.APP_ID, URLEncoder.encode(Constants.PROJECT_URL + "/demo/hello", "UTF-8"), Constants.WEB_PAGE_SCOPE_USER_INFO, "123");
        response.sendRedirect(accessUrl);
    }

}