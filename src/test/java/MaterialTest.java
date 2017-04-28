import com.alibaba.fastjson.JSONObject;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pre.my.test.demo.service.IAccountService;
import pre.my.test.robot.dto.material.AppendNewsMaterial;
import pre.my.test.robot.dto.material.BaseNews;
import pre.my.test.robot.dto.material.NewsMaterial;
import pre.my.test.robot.service.INewsMaterialService;
import pre.my.test.robot.util.AccessTokenUtil;
import pre.my.test.robot.util.Constants;
import pre.my.test.robot.util.MaterialUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class MaterialTest {

    private static final Logger logger = LoggerFactory.getLogger(MassTest.class);
    @Autowired
    private INewsMaterialService newsMaterialService;
    @org.junit.Test
    public void test() throws IOException {
        BaseNews[]  baseNewsArr= new BaseNews[1];
        BaseNews baseNews = new BaseNews();
        baseNews.setTitle("惊！！！swpu911..");
        baseNews.setThumb_media_id("IKj-yH_Yv8SQ-YBDmc-hILP8FcK3T4H8KUtCQRxr13A");
        baseNews.setAuthor("曾强");
        baseNews.setDigest("这是一个测试素材");
        baseNews.setShow_cover_pic("1");
        baseNews.setContent("swpu911测试新添永久图文素材");
        baseNews.setContent_source_url(Constants.PROJECT_URL + "/original/text?name=text");
        baseNewsArr[0] = baseNews;
        AppendNewsMaterial appendNewsMaterial =new AppendNewsMaterial();
        appendNewsMaterial.setArticles(baseNewsArr);
        JSONObject jsonObject = MaterialUtil.appendNewsMaterial(AccessTokenUtil.getValidAccessToken().getToken(), JSONObject.toJSONString(appendNewsMaterial));
        String media_id= jsonObject.getString("media_id");
        if(media_id!=null){
            List<NewsMaterial> newsMaterials= newsMaterialData(appendNewsMaterial,media_id);
            for(NewsMaterial newsMaterial:newsMaterials){
                newsMaterialService.save(newsMaterial);
            }
        }
    }
    protected List<NewsMaterial> newsMaterialData(AppendNewsMaterial appendNewsMaterial, String media_id){
        List<NewsMaterial> materials = new ArrayList<>();
        for(BaseNews baseNews:appendNewsMaterial.getArticles()){
            NewsMaterial newsMaterial  = new NewsMaterial();
            newsMaterial.setTitle(baseNews.getTitle());
            newsMaterial.setThumb_media_id(baseNews.getThumb_media_id());
            newsMaterial.setAuthor(baseNews.getAuthor());
            newsMaterial.setDigest(baseNews.getDigest());
            newsMaterial.setShow_cover_pic(baseNews.getShow_cover_pic());
            newsMaterial.setContent(baseNews.getContent());
            newsMaterial.setContent_source_url(baseNews.getContent_source_url());
            newsMaterial.setNewsMediaId(media_id);
            materials.add(newsMaterial);
        }
        return materials;
    }
}
