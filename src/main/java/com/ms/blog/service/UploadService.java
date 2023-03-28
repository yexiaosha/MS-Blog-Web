package com.ms.blog.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传业务接口
 * @author gary
 * @date 2023/3/28 16:13
 */
public interface UploadService {
    /**
     * 上传图片
     * @param file 文件对象
     * @return 结果
     */
    String uploadQNImg(MultipartFile file);

    String getPrivateFile(String fileKey);

    boolean removeFile(String bucketName, String fileKey);
}
