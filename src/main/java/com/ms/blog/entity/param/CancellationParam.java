package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
@ApiModel("用户注销表单参数")
public class CancellationParam extends PageParam {

    @ApiModelProperty("用户名")
    @Length(min = 3, max = 20)
    @NotNull
    private String username;

    @ApiModelProperty("邮箱")
    @Email
    @NotNull
    private String email;

    @ApiModelProperty("注销请求理由")
    @Length(min = 200, max = 250, message = "注销申请必须在200个字符以上，250个字符以内")
    private String reasonText;

}
