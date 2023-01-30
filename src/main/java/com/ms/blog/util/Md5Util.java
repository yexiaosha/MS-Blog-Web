package com.ms.blog.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密组件
 * @author wyh
 * @date 2023/01/11 10:28
 */
public class Md5Util {
    public static String encodePassword(String password){
        return DigestUtils.md5Hex(password);
    }

}
