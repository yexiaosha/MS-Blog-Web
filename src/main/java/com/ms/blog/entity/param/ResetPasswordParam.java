package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 更改密码/找回密码表单
 * @author wyh
 * @date 2023/01/13 11:02
 */
@Data
@ApiModel("更改密码/找回密码表单")
public class ResetPasswordParam {
    private String username;
    private String password;
    private String email;
    private String emailVerifyCode;
    private String captcha;
}
