package com.ms.blog.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.ms.blog.common.aspect.annotation.ControllerLog;
import com.ms.blog.util.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.util.IOUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码接口
 * @author wyh
 * @date 2023/01/10 11:47
 */
@RestController
@Api(tags = "验证码接口")
@RequestMapping("/msblog/captcha")
public class CaptchaController {

    @Resource
    private Producer producer;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/default")
    @ApiOperation("获取默认类型验证码")
    @ControllerLog("默认验证码获取")
    public void getDefaultCaptcha(HttpServletResponse response, HttpServletRequest request){
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        String uuid = UUIDUtils.getUUID();
        redisTemplate.opsForValue().set(uuid, text, 90 , TimeUnit.SECONDS);
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
}