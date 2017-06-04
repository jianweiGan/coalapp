package com.skyblue.coalapp.server.framework;


import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import com.qiniu.util.StringMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/app/imageUtil")
public class ImageUtil {

    String ACCESS_KEY = "Dv1hWgZr--j2d1boSh0uO7NaFigCFOxx-ESm_Nfn";
    String SECRET_KEY = "GRLfsQM4DxgfPvRPI4vANXYvCURjSgJBW9MnLy1k";

    //要上传的空间
    String bucketName = "coalapp";

    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    Configuration cfg = new Configuration(Zone.zone2());
    UploadManager uploadManager = new UploadManager(cfg);

    /*
    * <bucket>，表示允许用户上传文件到指定的 bucket。在这种格式下文件只能“新增”，若已存在同名资源则会失败。
    * <bucket>:<key>，表示只允许用户上传指定key的文件。在这种格式下文件默认允许“修改”，已存在同名资源则会被本次覆盖。
    */
    //获取上传凭证
    @RequestMapping("/getImageUpToken")
    private String getUpToken(String filename){

        String token = "";
        if(StringUtils.isBlank(filename)){
            token = auth.uploadToken(bucketName);
        }else{
            token = auth.uploadToken(bucketName, filename);
        }

        return token;
    }

    //上传文件名 支持 "" --> 名称为空
    //          支持 null --> 文件名的hash
    @RequestMapping("/uploadImage")
    public String upload(@RequestParam MultipartFile file) throws QiniuException {

            String suffix= file.getName().substring(file.getName().lastIndexOf(".")+1);
            String fileName = StringUtil.generateRandomString(null)+"."+suffix;

            String token = getUpToken(fileName);

            //Response response = uploadManager.put(file, fileName, token);

            return "http://or0qspriu.bkt.clouddn.com/"+fileName;
    }
}
