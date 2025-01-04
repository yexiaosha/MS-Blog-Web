package com.ms.blog.entity.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 更改密码/找回密码表单
 * @author wyh
 * @date 2023/01/13 11:02
 */
@Data
@Schema(name = "更改密码/找回密码表单")
public class ResetPasswordParam {

    @Schema(name = "用户名")
    private String username;

    @Schema(name = "密码")
    private String password;

    @Schema(name = "邮箱")
    private String email;

    @Schema(name = "邮箱验证码")
    private String code;

    @Schema(name = "token")
    private String token;
}
