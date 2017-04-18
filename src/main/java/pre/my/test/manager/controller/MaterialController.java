package pre.my.test.manager.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pre.my.test.robot.dto.material.Material;
import pre.my.test.robot.dto.material.MaterialBatchGetParam;
import pre.my.test.robot.dto.material.MaterialCount;
import pre.my.test.robot.dto.material.detail.Materials;
import pre.my.test.robot.service.IMaterialService;
import pre.my.test.robot.util.AccessTokenUtil;
import pre.my.test.robot.util.HttpConnectUtil;
import pre.my.test.robot.util.MaterialUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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

    @RequestMapping(value = "toMaterialPic", method = RequestMethod.GET)
    public String toMaterialPic() {

        return "material/material";
    }

    @RequestMapping(value = "/getMaterialCount", method = RequestMethod.POST)
    public String getMaterialCount(HttpServletRequest request) throws IOException {
        MaterialCount materialCount = MaterialUtil.getMaterialCount(AccessTokenUtil.getValidAccessToken().getToken());
        if (materialCount != null) {
            logger.debug("image: {},news: {},video: {},voice: {}", materialCount.getImage_count(), materialCount.getNews_count(),
                    materialCount.getVideo_count(), materialCount.getVoice_count());

            request.setAttribute("materialCount", materialCount);
            MaterialCount s = (MaterialCount) request.getSession().getAttribute("materialCount");
        }
        return "material/material";
    }

    @RequestMapping(value = "/getMaterials", method = RequestMethod.POST)
    @ResponseBody
    public void getMaterials(HttpServletRequest request) throws IOException {
        MaterialBatchGetParam param = new MaterialBatchGetParam();
        param.setType(request.getParameter("type"));
        param.setOffset(request.getParameter("offset"));
        param.setCount(request.getParameter("count"));
        Materials materials = MaterialUtil.getMaterialList(AccessTokenUtil.getValidAccessToken().getToken(), param);
        logger.debug(JSONObject.toJSONString(materials));
    }

    @RequestMapping(value = "/fileUploadLocal", method = RequestMethod.POST)
    public String fileUpload(@RequestParam(value = "file", required = false) MultipartFile file,
                             HttpServletRequest request) throws IOException {
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
        logger.debug("local absolute path:{}", pathRoot + path);
        Material material = HttpConnectUtil.upload(pathRoot + path, AccessTokenUtil.getValidAccessToken().getToken(), "image");
        if (material != null) {
            material.setName(fileName);
            material.setPath(pathRoot + path);
            materialService.save(material);
            logger.debug("fileName:{} mediaId:{}", fileName, material.getMediaId());
        }
        return "template/success";
    }
}
