package com.ms.blog.entity.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * 用户登录参数类
 * @author wyh
 * @date 2023/01/10 11:12
 */
@Data
@Schema(name ="登录参数类")
public class LoginParam {

    @NotBlank
    @Size(max = 25, min = 3, message = "用户名必须大于{min}, 小于{max}个字符")
    @Schema(description ="登录名 邮箱或者用户名")
    private String account;

    @NotBlank
    @Size(min = 8, message = "密码必须大于{min}个字符")
    @Schema(description ="密码")
    private String password;

    @NotNull
    @Min(0)
    @Max(1)
    @Schema(description = "登陆方式 0用户名 1邮箱")
    private Integer type;

    @NotBlank
    @Schema(description = "验证码UUID")
    private String uuid;

    @NotBlank
    @Schema(description ="验证码")
    private String captcha;
}
