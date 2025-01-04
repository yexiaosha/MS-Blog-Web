package com.ms.blog.controller;

import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ControllerLog;
import com.ms.blog.service.MailService;
import com.ms.blog.util.ResultUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@Tag(name = "邮件收发接口")
@CrossOrigin
public class MailController {

    @Resource
    private MailService mailService;

    @GetMapping("/send")
    @Operation(description = "发送邮箱验证码")
    @ControllerLog("发送邮箱验证码")
    @Parameters(
            @Parameter(name = "email", description = "邮箱")
    )
    public Result<Integer> sentMailVerifyCode(@RequestParam String email){
        mailService.sentMailVerifyCode(email);
        return ResultUtils.success("发送成功");
    }

}
