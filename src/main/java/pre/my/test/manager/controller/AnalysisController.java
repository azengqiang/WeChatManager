package pre.my.test.manager.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pre.my.test.robot.dto.analysis.*;
import pre.my.test.robot.dto.menu.MenuAnalysis;
import pre.my.test.robot.dto.user.MsgBack;
import pre.my.test.robot.dto.user.SubscribeAnalysis;
import pre.my.test.robot.service.IMenuAnalysisService;
import pre.my.test.robot.service.IMsgBackService;
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
    @Autowired
    private IMsgBackService msgBackService;

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

    @RequestMapping(value = "messageAnalysis", method = RequestMethod.GET)
    public void messageAnalysis(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        logger.debug("消息分析 startDate：" + startDate + "  endDate: " + endDate);

        MessageQuery messageQuery = new MessageQuery();
        messageQuery.setStartDate(startDate);
        messageQuery.setEndDate(endDate);
        //获取消息发送次数
        List<MsgBack> sendTimeList = msgBackService.select(messageQuery);
        int sendTimeNum = sendTimeList.size();
        //获取消息发送人数
        List<MsgBack> sendUserList = msgBackService.selectUserNum(messageQuery);
        int sendUserNum = sendUserList.size();
        //设置消息分析参数
        MessageQueryAnalysis messageQueryAnalysis = new MessageQueryAnalysis();
        messageQueryAnalysis.setSendTimeNum(sendTimeNum);
        messageQueryAnalysis.setSendUserNum(sendUserNum);
        if(0==sendUserNum){
            messageQueryAnalysis.setPerCapitaNum(0.0);
        }else{
            messageQueryAnalysis.setPerCapitaNum((sendTimeNum * 1.0) / sendUserNum);
        }
        //回调
        response.setCharacterEncoding("UTF-8"); //设置编码格式
        response.setContentType("text/html");   //设置数据格式
        PrintWriter out = response.getWriter(); //获取写入对象
        out.print(JSONObject.toJSON(messageQueryAnalysis));
        out.flush();

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
        menuQueryAnalysis.setClickNum(clickNum);
        menuQueryAnalysis.setUserNum(userNum);
        if(0==userNum){
            menuQueryAnalysis.setPerCapitaNum(0.0);
        }else {
            menuQueryAnalysis.setPerCapitaNum((clickNum * 1.0) / userNum);
        }
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
        int totalUnSubscribe = subscribeDetailService.select(subscribeQuery).size();
        subscribeQuery.setAction("subscribe");
        int totalSubscribe = subscribeDetailService.select(subscribeQuery).size();
        int total = totalSubscribe - totalUnSubscribe;
        //设置用户分析参数
        SubscribeQueryAnalysis subscribeQueryAnalysis = new SubscribeQueryAnalysis();
        subscribeQueryAnalysis.setSubscribeNum(sub);
        subscribeQueryAnalysis.setUnSubscribeNum(unSub);
        subscribeQueryAnalysis.setNetSubscribeNum(sub - unSub);
        subscribeQueryAnalysis.setTotalSubscribeNum(total);
        //回调
        response.setCharacterEncoding("UTF-8"); //设置编码格式
        response.setContentType("text/html");   //设置数据格式
        PrintWriter out = response.getWriter(); //获取写入对象
        out.print(JSONObject.toJSON(subscribeQueryAnalysis));
        out.flush();
    }


}
