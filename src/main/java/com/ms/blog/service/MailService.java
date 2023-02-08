package com.ms.blog.service;

import com.ms.blog.common.Result;

/**
 * 邮箱收发服务接口
 * @author wyh
 * @date 2023/01/13 14:33
 */
public interface MailService {

    /**
     * 发送邮箱验证码
     * @param email 邮箱
     */
    void sentMailVerifyCode(String email);

    /**
     * 邮箱验证码验证
     * @param code 验证码
     * @return 验证结果
     */
    Result<Integer> verifyMailCode(String code);
}
