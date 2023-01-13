package com.ms.blog.service;

import com.ms.blog.common.Result;

/**
 * 验证码业务接口
 * @author wyh
 * @date 2023/01/10 17:07
 */
public interface CaptchaService {

    /**
     * 验证验证码
     * @param captcha 验证码
     * @return  结果
     */
    Result<Integer> verifyCaptcha(String captcha);
}
