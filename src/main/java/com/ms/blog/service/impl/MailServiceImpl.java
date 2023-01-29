package com.ms.blog.service.impl;

import com.ms.blog.common.ErrorCode;
import com.ms.blog.common.Result;
import com.ms.blog.common.aspect.annotation.ServiceLog;
import com.ms.blog.service.MailService;
import com.ms.blog.util.ResultUtils;
import com.ms.blog.util.UUIDUtils;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 邮箱收发服务实现
 * @author wyh
 * @date 2023/01/13 14:36
 */
@Service
public class MailServiceImpl implements MailService {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender mailSender;

    private static final String MAIL_ = "MAIL_";

    @Override
    @ServiceLog("邮件发送")
    public Result<Integer> sentMailVerifyCode(String email) {
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        redisTemplate.opsForValue().set(MAIL_ + checkCode, UUIDUtils.getUUID(), 30, TimeUnit.MINUTES);
        String message = "您在MineSpaceBlog收发的邮箱验证码为：" + checkCode + "\n" + "请您在30分钟内使用此验证码";
        try {
            sendSimpleMail(email,"MineSpace验证服务",message);
        }catch (Exception e){
            return ResultUtils.fail(ErrorCode.EMAIL_SEND_ERROR.getCode(), ErrorCode.EMAIL_SEND_ERROR.getMsg());
        }
        return ResultUtils.success("邮件已发送");
    }

    @Override
    @ServiceLog("邮件验证码验证")
    public Result<Integer> verifyMailCode(String code) {
        if (redisTemplate.opsForValue().get(MAIL_ + code) == null){
            return ResultUtils.fail(ErrorCode.EMAIL_VERIFY_FAIL.getCode(), ErrorCode.EMAIL_VERIFY_FAIL.getMsg());
        }
        redisTemplate.delete(MAIL_ + code);
        return ResultUtils.success("验证成功");
    }

    @Async
    public void sendSimpleMail(String to, String title, String content){
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(from);
            mailMessage.setTo(to);
            mailMessage.setSubject(title);
            mailMessage.setText(content);
            mailSender.send(mailMessage);
    }

}
