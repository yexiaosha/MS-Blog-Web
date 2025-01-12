package com.ms.blog.service.impl;

import com.google.gson.Gson;
import com.ms.blog.common.annotation.ServiceLog;
import com.ms.blog.config.QiniuConfig;
import com.ms.blog.service.UploadService;
import com.ms.blog.util.QiniuUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import io.netty.util.internal.StringUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 上传业务实现
 * @author gary
 * @date 2023/3/28 16:14
 */
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    @Resource
    private QiniuConfig qiNiuYunConfig;

    /**七牛文件上传管理器*/
    private UploadManager uploadManager;
    /**上传的token*/
    private String token;
    /**七牛认证管理*/
    private Auth auth;

    private BucketManager bucketManager;

    public UploadServiceImpl(QiniuConfig qiniuConfig) {
        this.qiNiuYunConfig = qiniuConfig;
        init();
    }

    private void init() {
        // 我是华南地区的所以是zone2，如果是其他地区的需要修改
        uploadManager = new UploadManager(new Configuration(Zone.zone2()));
        auth = Auth.create(qiNiuYunConfig.getAccessKey(), qiNiuYunConfig.getSecretKey());
        // 根据命名空间生成的上传token
        bucketManager = new BucketManager(auth, new Configuration(Zone.zone2()));
        token = auth.uploadToken(qiNiuYunConfig.getBucketName());
    }

    @Override
    @ServiceLog("图片上传")
    public String uploadQNImg(MultipartFile file) {
        String resultImage = "失败";
        try {
            // 判断图片后缀，并使用工具类根据上传文件生成唯一图片名称,防止截断字符如“%00”
            String fileName = file.getOriginalFilename();
            if (StringUtil.isNullOrEmpty(fileName)){
                return resultImage;
            }
            String imgName = QiniuUtil.getRandomImgName(fileName);

            //判断是否为恶意程序
            //通过流的方式把文件转换为BufferedImage对象,获取宽和高,只有图片才具有宽高属性
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if(bufferedImage == null || bufferedImage.getHeight()==0 || bufferedImage.getWidth()==0){
                return resultImage;
            }

            // 上传图片文件
            Response res = uploadManager.put(file.getInputStream(), imgName, token, null, null);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res);
            }
            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);
            log.info("上传结果是：{}",putRet.toString());
            // 直接返回外链地址
            return getPrivateFile(imgName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "失败";
    }

    @Override
    @ServiceLog("获取私人空间文件")
    public String getPrivateFile(String fileKey) {
        String encodedFileName;
        String finalUrl = null;
        try {
            encodedFileName = URLEncoder.encode(fileKey, "utf-8").replace("+", "%20");
            String publicUrl = String.format("%s/%s", this.qiNiuYunConfig.getUrl(), encodedFileName);
            //1小时，可以自定义链接过期时间
            long expireInSeconds = 3600;
            finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return finalUrl;
    }


    @Override
    public boolean removeFile(String bucketName, String fileKey) {
        try {
            bucketManager.delete(bucketName, fileKey);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return true;
    }


}
