package com.ms.blog.service.impl;

import com.google.code.kaptcha.Producer;
import com.ms.blog.common.Result;
import com.ms.blog.service.CaptchaService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 验证码业务接口实现
 * @author wyh
 * @date 2023/01/10 17:21
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Resource
    Producer producer;

    @Override
    public Result createDefaultCaptcha(String captcha) {
        return null;
    }
}
