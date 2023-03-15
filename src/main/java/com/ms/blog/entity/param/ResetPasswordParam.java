package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 更改密码/找回密码表单
 * @author wyh
 * @date 2023/01/13 11:02
 */
@Data
@ApiModel("更改密码/找回密码表单")
public class ResetPasswordParam {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("邮箱验证码")
    private String emailVerifyCode;

    @ApiModelProperty("验证码")
    private String captcha;
}
