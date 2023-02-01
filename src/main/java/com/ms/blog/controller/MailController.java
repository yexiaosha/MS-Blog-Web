package com.ms.blog.controller;

import com.ms.blog.common.Result;
import com.ms.blog.common.aspect.annotation.ControllerLog;
import com.ms.blog.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 邮件收发接口
 * @author wyh
 * @date 2023/01/13 13:48
 */
@RestController
@RequestMapping("msblog/mail")
@Api(tags = "邮件收发接口")
public class MailController {

    @Resource
    private MailService mailService;

    @GetMapping("/send")
    @ApiOperation("发送邮箱验证码")
    @ControllerLog("发送邮箱验证码")
    public Result<Integer> sentMailVerifyCode(@RequestParam String email){
        return mailService.sentMailVerifyCode(email);
    }

}