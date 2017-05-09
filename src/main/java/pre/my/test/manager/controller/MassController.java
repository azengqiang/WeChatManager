package pre.my.test.manager.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pre.my.test.robot.dto.mass.Filter;
import pre.my.test.robot.dto.mass.MText;
import pre.my.test.robot.dto.mass.TextMass;
import pre.my.test.robot.dto.user.Group;
import pre.my.test.robot.dto.user.Remark;
import pre.my.test.robot.service.IGroupService;
import pre.my.test.robot.util.AccessTokenUtil;
import pre.my.test.robot.util.MassUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/27.
 */
@Controller
@RequestMapping(value = "/admin")
public class MassController {
    private static final Logger logger = LoggerFactory.getLogger(MassController.class);

    @Autowired
    private IGroupService service;

    @RequestMapping(value = "/toMassMessage", method = RequestMethod.GET)
    public String toMassMessage(HttpServletRequest request, HttpServletResponse response) {
        List<Group> groups = service.selectAll();
        request.setAttribute("massGroups", groups);
        return "message/mass";
    }

    @RequestMapping(value = "/massTextMessage", method = RequestMethod.POST)
    public String massTextMessage(HttpServletRequest request, HttpServletResponse response, @RequestBody Remark remark) throws IOException {
        String massMsg = null;
        TextMass textMass = new TextMass();
        textMass.setText(new MText(remark.getRemark()));
        textMass.setFilter(new Filter(false, remark.getOpenid()));
        textMass.setMsgtype("text");
        massMsg =  JSON.toJSONString(textMass);
        logger.debug("massMsg: {}", massMsg);
        JSONObject jsonObject = MassUtil.groupMass(AccessTokenUtil.getValidAccessToken().getToken(), massMsg);
        if (jsonObject.getInteger("errcode") == 0) {
            logger.debug("群发成功 {}", jsonObject.toJSONString());
        } else {
            logger.debug("群发失败 {}",jsonObject.toJSONString());
        }
        return "message/mass";
    }


}
