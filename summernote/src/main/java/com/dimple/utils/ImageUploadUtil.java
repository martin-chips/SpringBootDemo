package com.dimple.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : ImageUploadUtil
 * @description :
 * @date : 12/19/18 10:45
 */
public class ImageUploadUtil {
    /**
     * 默认支持的文件类型(如果你使用的是Springboot的话，还可以从配置文件中注入进来)
     */
    private static String allowImgFormat = "gif,jpg,jpeg,png";

    /**
     * 图像上传
     *
     * @param file 图片文件
     * @return 文件的URL地址（此处的地址返回格式为<code>/images/abc.jpg</code>）
     * @throws FileUploadBase.FileSizeLimitExceededException
     */
    public static String imgUpload(MultipartFile file) throws FileUploadBase.FileSizeLimitExceededException {
        /**
         * 可以对上传文件的大小，名字长度进行一个校验
         */
        //这里是对上传文件的大小做一个校验，该方法未实现具体逻辑
        checkoutFileSize(file);

        String originalFilename = file.getOriginalFilename();
        //获取扩展名
        String extension = FilenameUtils.getExtension(originalFilename);

        //生成随机名字
        String fileName = generateFileName(originalFilename, extension);

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = file.getInputStream();
            //输出位置采用了硬编码，这样不好，只是示范，别这样做
            outputStream = new FileOutputStream("D:/rentHouse" + "/" + fileName);
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return "/images/" + fileName;
    }

    /**
     * 根据URL对图片进行删除
     *
     * @param url 传入uri的格式为：<p>images/abcd.jpg</p>
     * @return
     */
    public static Boolean deleteImgByUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return false;
        }
        int i = url.lastIndexOf("/");
        //获取文件的名字
        String name = url.substring(i + 1);
        File file = new File("D:/rentHouse/" + name);
        if (!file.exists()) {
            return false;
        }
        try {
            FileUtils.forceDelete(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 生成文件名
     * <p>这里的名字生成的就很随意了，直接采用的是UUID的，可以用其他的方式来生成名字 </p>
     *
     * @param fileName  原始的名称
     * @param extension 扩展名
     * @return 生成的文件名
     */
    private static String generateFileName(String fileName, String extension) {
        fileName.replace("_", "");
        return UUID.randomUUID().toString().substring(0, 7) + "." + extension;
    }

    /**
     * 校验文件的大小是否合适
     *
     * @param file
     */
    private static void checkoutFileSize(MultipartFile file) throws FileUploadBase.FileSizeLimitExceededException {
        long size = file.getSize();
        /**
         * 在此处做具体的验证逻辑
         */
    }

    /**
     * 设置路径
     *
     * @param dir      路径地址
     * @param filename 文件名称
     * @return
     * @throws IOException
     */
    private static final File getAbsoluteFile(String dir, String filename) throws IOException {
        File desc = new File(File.separator + filename);

        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }
}
