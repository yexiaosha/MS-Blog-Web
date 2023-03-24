package com.ms.blog.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 七牛云存储工具类
 * @author Anna.
 * @date 2023/3/21 18:22
 */

@Component
public class QiniuUtil {

    @Value("${qiniu.access-key}")
    String ACCESS_KEY ;

    @Value("${qiniu.secret-key}")
    String SECRET_KEY;


}
