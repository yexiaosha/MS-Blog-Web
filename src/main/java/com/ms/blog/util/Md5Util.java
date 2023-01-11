package com.ms.blog.util;

import java.security.MessageDigest;

/**
 * 加密组件
 * @author wyh
 * @date 2023/01/11 10:28
 */
public class Md5Util {

    public static final String KEY_MD5 = "MD5";

    /***
     * MD5加密（生成唯一的MD5值）
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encodeMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
        return md5.digest();
    }

    public static String encodePassword(String password){
        try {
            String encode = encodeMD5(password.getBytes()).toString();
            return encode;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
