package pre.my.test.manager.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pre.my.test.robot.dto.mass.MImage;
import pre.my.test.robot.dto.material.*;
import pre.my.test.robot.dto.user.UserInfo;
import pre.my.test.robot.service.IMaterialService;
import pre.my.test.robot.service.INewsMaterialService;
import pre.my.test.robot.util.AccessTokenUtil;
import pre.my.test.robot.util.Constants;
import pre.my.test.robot.util.HttpConnectUtil;
import pre.my.test.robot.util.MaterialUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Author:qiang.zeng on 2017/3/27.
 */
@Controller
@RequestMapping(value = "/admin")
public class MaterialController {
    private static final Logger logger = LoggerFactory.getLogger(MaterialController.class);
    @Autowired
    private IMaterialService materialService;
    @Autowired
    private INewsMaterialService newsMaterialService;

    @RequestMapping(value = "toMaterialPic", method = RequestMethod.GET)
    public String toMaterialPic(HttpServletRequest request) {
        List<Material> pictures = materialService.selectAll();
        request.setAttribute("pictures", pictures);
        return "material/picture";
    }

    @RequestMapping(value = "toMaterialNews", method = RequestMethod.GET)
    public String toMaterialNews(HttpServletRequest request) {
        List<Material> pictures = materialService.selectAll();
        request.setAttribute("pictures", pictures);
        return "material/news";
    }

    @RequestMapping(value = "toMaterialList", method = RequestMethod.GET)
    public String toMaterialList() {
        return "material/list";
    }

    @RequestMapping(value = "deleteNews", method = RequestMethod.POST)
    public String deleteNews(HttpServletRequest request, @RequestBody List<NewsMaterial> newsMaterialList) throws IOException {
        for (NewsMaterial newsMaterial : newsMaterialList) {
            MImage mImage = new MImage();
            mImage.setMedia_id(newsMaterial.getNewsMediaId());
            JSONObject jsonObject = MaterialUtil.deleteMaterial(AccessTokenUtil.getValidAccessToken().getToken(), JSON.toJSONString(mImage));
            if (0 == jsonObject.getInteger("errcode")) {
                NewsMaterial temp = new NewsMaterial();
                temp.setNewsMediaId(newsMaterial.getNewsMediaId());
                NewsMaterial exitNewsMaterial = newsMaterialService.selectBy(temp);
                if (exitNewsMaterial != null) {
                    logger.debug("本地删除图文素材{}", exitNewsMaterial.getTitle());
                    newsMaterialService.delete(newsMaterial);
                } else {
                    logger.debug("查无此图文素材");
                }
            } else {
                logger.debug("微信删除图文素材失败");
            }
        }
        return "redirect:toMaterialNews";
    }
    @RequestMapping(value = "/addNews", method = RequestMethod.POST)
    public String getMaterials(HttpServletRequest request, @RequestBody List<BaseNews> baseNewsList) throws IOException {
        BaseNews[] baseNewsArr = baseNewsList.toArray(new BaseNews[baseNewsList.size()]);
        AppendNewsMaterial appendNewsMaterial = new AppendNewsMaterial();
        appendNewsMaterial.setArticles(baseNewsArr);
        JSONObject jsonObject = MaterialUtil.appendNewsMaterial(AccessTokenUtil.getValidAccessToken().getToken(), JSONObject.toJSONString(appendNewsMaterial));
        String media_id = jsonObject.getString("media_id");
        //String media_id = "IKj-yH_Yv8SQ-YBDmc-hIFADTcVlF6JBb75qdM_ajMw";
        if (media_id != null) {
            List<NewsMaterial> newsMaterials = newsMaterialData(appendNewsMaterial, media_id);
            for (NewsMaterial newsMaterial : newsMaterials) {
                newsMaterialService.save(newsMaterial);
            }
        }
        return "redirect:toMaterialNews";
    }

    @RequestMapping(value = "/newsInfo", method = RequestMethod.GET)
    @ResponseBody
    public void newsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //得到客户端传递的页码和每页记录数，并转换成int类型
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        String searchText = request.getParameter("searchText");

        List<NewsMaterial> newsMaterials = newsMaterialService.select(pageSize, pageNumber, new NewsMaterial());
        int total = newsMaterialService.getSelectSize(new NewsMaterial()).size();

        response.setCharacterEncoding("UTF-8"); //设置编码格式
        response.setContentType("text/html");   //设置数据格式
        PrintWriter out = response.getWriter(); //获取写入对象

        String json = "{\"total\":" + total + ",\"rows\":" + JSON.toJSONString(newsMaterials) + "}";
        out.print(json);
        out.flush();
    }

    /**
     * 构造图文素材数据
     *
     * @param appendNewsMaterial
     * @param media_id
     * @return
     */
    protected List<NewsMaterial> newsMaterialData(AppendNewsMaterial appendNewsMaterial, String media_id) {
        List<NewsMaterial> materials = new ArrayList<>();
        for (BaseNews baseNews : appendNewsMaterial.getArticles()) {
            NewsMaterial newsMaterial = new NewsMaterial();
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

    /**
     * 获取素材列表
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getMaterials", method = RequestMethod.POST)
    public void getMaterials(HttpServletRequest request,HttpServletResponse response,@RequestBody MaterialBatchGetParam param) throws IOException {
        Materials materials = MaterialUtil.getMaterialList(AccessTokenUtil.getValidAccessToken().getToken(), param);
        logger.debug(JSONObject.toJSONString(materials));
       /* request.setAttribute("materials", JSONObject.toJSONString(materials));
        return "material/list";*/
        response.setCharacterEncoding("UTF-8"); //设置编码格式
        response.setContentType("text/html");   //设置数据格式
        PrintWriter out = response.getWriter(); //获取写入对象
        out.print(JSONObject.toJSON(materials));
        out.flush();
    }

    /**
     * 获取素材数量
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getMaterialCount", method = RequestMethod.POST)
    public void getMaterialCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MaterialCount materialCount = MaterialUtil.getMaterialCount(AccessTokenUtil.getValidAccessToken().getToken());
        logger.debug("image: {},news: {},video: {},voice: {}", materialCount.getImage_count(), materialCount.getNews_count(),
                    materialCount.getVideo_count(), materialCount.getVoice_count());
       /* request.setAttribute("materialCount", materialCount);
        return "material/list";*/
        response.setCharacterEncoding("UTF-8"); //设置编码格式
        response.setContentType("text/html");   //设置数据格式
        PrintWriter out = response.getWriter(); //获取写入对象
        out.print(JSONObject.toJSON(materialCount));
        out.flush();
    }

    @RequestMapping(value = "/fileUploadLocal", method = RequestMethod.POST)
    public String fileUpload(@RequestParam(value = "file", required = false) MultipartFile file,
                             HttpServletRequest request) throws IOException {
        if (null == file) {
            return "redirect:toMaterialPic";
        }
        //获得物理路径webapp所在路径
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        String path = "";
        String fileName = file.getOriginalFilename();

        if (!file.isEmpty()) {
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType = file.getContentType();
            //获得文件后缀名称
            String imageName = contentType.substring(contentType.indexOf("/") + 1);
            path = "/resources/images/" + uuid + "." + imageName;
            file.transferTo(new File(pathRoot + path));
        }
        request.setAttribute("imagesPath", path);
        logger.debug("local path:{}", path);
        Material material = HttpConnectUtil.upload(pathRoot + path, AccessTokenUtil.getValidAccessToken().getToken(), "image");
        if (material != null) {
            material.setName(fileName);
            material.setPath(path);
            materialService.save(material);
            logger.debug("fileName:{} mediaId:{}", fileName, material.getMediaId());
        }
        //  return "template/success";
        return "redirect:toMaterialPic";
    }

    @RequestMapping(value = "deletePicture", method = RequestMethod.POST)
    //@ResponseBody
    public String deletePicture(HttpServletRequest request, @RequestBody List<Material> materials) throws IOException {
        for (Material material : materials) {
            MImage mImage = new MImage();
            mImage.setMedia_id(material.getMediaId());
            JSONObject jsonObject = MaterialUtil.deleteMaterial(AccessTokenUtil.getValidAccessToken().getToken(), JSON.toJSONString(mImage));
            if (0 == jsonObject.getInteger("errcode")) {
                Material exitMaterial = materialService.selectMaterialByMediaId(material.getMediaId());
                if (exitMaterial != null) {
                    logger.debug("本地删除图片素材{}", exitMaterial.getName());
                    materialService.delete(material);
                } else {
                    logger.debug("查无此图片素材");
                }
            } else {
                logger.debug("微信删除图片素材失败");
            }
        }
        return "redirect:toMaterialPic";
    }

}
