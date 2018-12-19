package com.dimple.controller;

import com.dimple.utils.ImageUploadUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : SummernoteController
 * @description :
 * @date : 12/19/18 10:44
 */
@Controller
public class SummernoteController {
    //配置路径映射
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/image")
    @ResponseBody
    public String uploadImg(@RequestParam MultipartFile file) throws FileUploadBase.FileSizeLimitExceededException {
        String s = ImageUploadUtil.imgUpload(file);
        return s;
    }

    @DeleteMapping("/image")
    @ResponseBody
    public Boolean deleteImage(String imgUrl) {
        Boolean success = ImageUploadUtil.deleteImgByUrl(imgUrl);
        return success;
    }
}
