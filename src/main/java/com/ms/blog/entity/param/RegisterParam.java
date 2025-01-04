package com.ms.blog.entity.param;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 游客注册表单参数
 * @author wyh
 * @date 2023/01/11 16:29
 */
@Data
@Schema(name ="游客注册表单参数")
public class RegisterParam {

    @Schema(name ="用户名")
    @Length(min = 3, max = 20, message = "用户名必须在3个字符以上，20个字符以内")
    @NotNull
    private String username;

    @Schema(name ="密码")
    @Length(min = 8, message = "密码必须八位以上")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,}$")
    private String password;

    @Schema(name ="邮箱")
    @Email
    @NotNull
    private String email;

    @Schema(name ="验证码")
    @Length(min = 4, max = 4, message = "验证码必须是规定位数")
    @NotNull
    private String captcha;

    @Schema(name ="用户名")
    @Length(min = 3, max = 20, message = "用户名必须在3个字符以上，20个字符以内")
    private String nickname = this.username;

    @Schema(name ="邮箱验证码")
    @NotNull
    @Length(min = 6, max = 6, message = "邮箱验证码必须是规定位数")
    private String code;
}
