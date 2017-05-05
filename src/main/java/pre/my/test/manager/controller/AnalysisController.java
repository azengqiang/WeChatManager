package pre.my.test.manager.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pre.my.test.robot.dto.analysis.MenuQuery;
import pre.my.test.robot.dto.analysis.MenuQueryAnalysis;
import pre.my.test.robot.dto.analysis.SubscribeQueryAnalysis;
import pre.my.test.robot.dto.menu.MenuAnalysis;
import pre.my.test.robot.dto.user.SubscribeAnalysis;
import pre.my.test.robot.dto.analysis.SubscribeQuery;
import pre.my.test.robot.service.IMenuAnalysisService;
import pre.my.test.robot.service.ISubscribeDetailService;
import pre.my.test.robot.service.IUserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

/**
 * Author:qiang.zeng on 2017/2/16.
 */
@Controller
@RequestMapping(value = "/admin")
public class AnalysisController {
    private static final Logger logger = LoggerFactory.getLogger(AnalysisController.class);
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private ISubscribeDetailService subscribeDetailService;
    @Autowired
    private IMenuAnalysisService menuAnalysisService;

    @RequestMapping(value = "toGraphicAnalysis", method = RequestMethod.GET)
    public String toGraphicAnalysis() {

        return "analysis/graphic";
    }

    @RequestMapping(value = "toMenuAnalysis", method = RequestMethod.GET)
    public String toMenuAnalysis(HttpServletRequest request, HttpServletResponse response) {

        return "analysis/menu";
    }

    @RequestMapping(value = "toMessageAnalysis", method = RequestMethod.GET)
    public String toMessageAnalysis(HttpServletRequest request, HttpServletResponse response) {

        return "analysis/message";
    }

    @RequestMapping(value = "toUserAnalysis", method = RequestMethod.GET)
    public String toUserAnalysis(HttpServletRequest request, HttpServletResponse response) {

        return "analysis/user";
    }
    @RequestMapping(value = "menuAnalysis", method = RequestMethod.GET)
    public void menuAnalysis(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        logger.debug("菜单分析 startDate：" + startDate + "  endDate: " + endDate);

        MenuQuery menuQuery = new MenuQuery();
        menuQuery.setStartDate(startDate);
        menuQuery.setEndDate(endDate);
        //获取菜单点击次数
        List<MenuAnalysis> clickNumList = menuAnalysisService.select(menuQuery);
        int clickNum = clickNumList.size();
        //获取菜单点击人数
        List<MenuAnalysis> userNumList = menuAnalysisService.selectUserNum(menuQuery);
        int userNum = userNumList.size();
        //设置菜单分析参数
        MenuQueryAnalysis menuQueryAnalysis = new MenuQueryAnalysis();
        menuQueryAnalysis.setClickNum(String.valueOf(clickNum));
        menuQueryAnalysis.setUserNum(String.valueOf(userNum));
        menuQueryAnalysis.setPerCapitaNum(String.valueOf(((float)clickNum)/userNum));
        //回调
        response.setCharacterEncoding("UTF-8"); //设置编码格式
        response.setContentType("text/html");   //设置数据格式
        PrintWriter out = response.getWriter(); //获取写入对象
        out.print(JSONObject.toJSON(menuQueryAnalysis));
        out.flush();

    }
    @RequestMapping(value = "userAnalysis", method = RequestMethod.GET)
    public void userAnalysis(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        logger.debug("用户分析 startDate：" + startDate + "  endDate: " + endDate);
        //String日期转换为Date
        //  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置查询参数
        SubscribeQuery subscribeQuery = new SubscribeQuery();
        subscribeQuery.setStartDate(startDate);
        subscribeQuery.setEndDate(endDate);
        //获取新关注列表
        subscribeQuery.setAction("subscribe");
        List<SubscribeAnalysis> subscribeList = subscribeDetailService.select(subscribeQuery);
        int sub = subscribeList.size();
        //获取取消关注列表
        subscribeQuery.setAction("unSubscribe");
        List<SubscribeAnalysis> unSubscribeList = subscribeDetailService.select(subscribeQuery);
        int unSub = unSubscribeList.size();
        //获取关注的总人数
        subscribeQuery.setStartDate("");
        subscribeQuery.setEndDate(endDate);
        int totalUnSubscribe= subscribeDetailService.select(subscribeQuery).size();
        subscribeQuery.setAction("subscribe");
        int totalSubscribe = subscribeDetailService.select(subscribeQuery).size();
        int total = totalSubscribe-totalUnSubscribe;
        //设置用户分析参数
        SubscribeQueryAnalysis subscribeQueryAnalysis = new SubscribeQueryAnalysis();
        subscribeQueryAnalysis.setSubscribeNum(String.valueOf(sub));
        subscribeQueryAnalysis.setUnSubscribeNum(String.valueOf(unSub));
        subscribeQueryAnalysis.setNetSubscribeNum(String.valueOf(sub - unSub));
        subscribeQueryAnalysis.setTotalSubscribeNum(String.valueOf(total));
        //回调
        response.setCharacterEncoding("UTF-8"); //设置编码格式
        response.setContentType("text/html");   //设置数据格式
        PrintWriter out = response.getWriter(); //获取写入对象
        out.print(JSONObject.toJSON(subscribeQueryAnalysis));
        out.flush();
    }


}
