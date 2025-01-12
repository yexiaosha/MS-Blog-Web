package com.ms.blog.service.impl;

import com.ms.blog.common.ErrorCode;
import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ServiceLog;
import com.ms.blog.service.CaptchaService;
import com.ms.blog.util.ResultUtils;
import jakarta.annotation.Resource;
import lombok.val;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 验证码业务接口实现
 * @author wyh
 * @date 2023/01/10 17:21
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {


    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private static final String CAPTCHA_ = "CAPTCHA_";

    @Override
    @ServiceLog("验证码验证")
    public Result<Integer> verifyCaptcha(String captcha, String uuid) {
        Object code = redisTemplate.opsForValue().get(CAPTCHA_ + uuid);
        if (code == null){
            return ResultUtils.fail(ErrorCode.CAPTCHA_ERROR.getCode(), ErrorCode.CAPTCHA_ERROR.getMsg());
        }

        if (!Objects.equals(captcha, code.toString())){
            return ResultUtils.fail(ErrorCode.CAPTCHA_ERROR.getCode(), ErrorCode.CAPTCHA_ERROR.getMsg());
        }

        redisTemplate.delete(CAPTCHA_ + uuid);

        return ResultUtils.success("验证成功");
    }
}
