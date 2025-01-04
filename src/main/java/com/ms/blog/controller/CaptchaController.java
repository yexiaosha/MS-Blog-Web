package com.ms.blog.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ControllerLog;
import com.ms.blog.service.CaptchaService;
import com.ms.blog.util.UUIDUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.apache.poi.util.IOUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码接口
 * @author wyh
 * @date 2023/01/10 11:47
 */
@RestController
@Tag(name = "验证码接口")
@RequestMapping("/msblog/captcha")
@CrossOrigin
public class CaptchaController {

    @Resource
    private Producer producer;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private CaptchaService captchaService;

    private static final String CAPTCHA_ = "CAPTCHA_";

    @GetMapping("/default")
    @Operation(description = "获取默认类型验证码")
    @ControllerLog("默认验证码获取")
    public void getDefaultCaptcha(HttpServletResponse response, HttpServletRequest request){
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        String text = producer.createText();
        String uuid = UUIDUtils.getUUID();
        BufferedImage image = producer.createImage(text);
        redisTemplate.opsForValue().set(CAPTCHA_ + text, uuid, 90 , TimeUnit.SECONDS);
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        IOUtils.closeQuietly(out);
    }

    @GetMapping("/verify")
    @Operation(description = "验证码验证")
    @ControllerLog("验证验证码")
    public Result<Integer> verifyCaptcha(@RequestParam @Validated @NotNull String captcha){
        return captchaService.verifyCaptcha(captcha);
    }
}
