package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 用户登录参数类
 * @author wyh
 * @date 2023/01/10 11:12
 */
@Data
@ApiModel("登录参数类")
public class LoginParam {

    @NotBlank
    @Size(max = 25, min = 3, message = "用户名必须大于{min}, 小于{max}个字符")
    @ApiModelProperty("登录名 邮箱或者用户名")
    private String account;

    @NotBlank
    @Size(min = 8, message = "密码必须大于{min}个字符")
    @ApiModelProperty("密码")
    private String password;

    @NotNull
    @Min(0)
    @Max(1)
    @ApiModelProperty("登陆方式 0用户名 1邮箱")
    private Integer type;

    @NotBlank
    @ApiModelProperty("验证码")
    private String captcha;
}
