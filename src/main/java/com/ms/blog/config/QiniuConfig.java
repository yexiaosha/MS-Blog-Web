package com.ms.blog.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 七牛云设置
 *
 * @author gary
 * @date 2023/3/27 16:33
 */
@Configuration
@Data
public class QiniuConfig {
    /**
     * 七牛域名domain
     */
    @Value("${qiniu.url}")
    private String url;
    /**
     * 七牛ACCESS_KEY
     */
    @Value("${qiniu.access-key}")
    private String AccessKey;
    /**
     * 七牛SECRET_KEY
     */
    @Value("${qiniu.secret-key}")
    private String SecretKey;
    /**
     * 七牛空间名
     */
    @Value("${qiniu.bucketName}")
    private String BucketName;
}
