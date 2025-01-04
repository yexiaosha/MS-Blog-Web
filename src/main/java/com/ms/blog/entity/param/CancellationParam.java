package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * 用户注销表单
 * @author wyh
 * @date 2023/01/13 16:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "用户注销表单参数")
public class CancellationParam extends PageParam {

    @Schema(name = "用户名")
    @Length(min = 3, max = 20)
    @NotNull
    private String username;

    @Schema(name = "邮箱")
    @Email
    @NotNull
    private String email;

    @Schema(name = "注销请求理由")
    @Length(min = 200, max = 250, message = "注销申请必须在200个字符以上，250个字符以内")
    private String reasonText;

}
