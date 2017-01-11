import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pre.my.test.demo.dto.User;
import pre.my.test.demo.service.IAccountService;


/**
 * Author:qiang.zeng@hand-china.com on 2017/1/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})

public class TestMybatis {
    private static Logger logger = Logger.getLogger(TestMybatis.class);
    @Autowired
    private IAccountService accountService = null;

  /*  private ApplicationContext ac = null;
    @Before
    public void before() {
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        accountService = (IAccountService) ac.getBean("accountService");
    }*/

    @Test
    public void test1() {
        User user = accountService.login((long) 1);
        // System.out.println(user.getUserName());
        // logger.info("值："+user.getUserName());
        logger.info(JSON.toJSONString(user));
    }
}
