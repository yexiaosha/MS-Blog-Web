package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 已处理注销请求表单
 * @author wyh
 * @date 2023/01/13 19:41
 */
@Data
@ApiModel("已处理的注销申请")
public class HandledCancellationParam extends PageParam {

    @ApiModelProperty("注销请求id")
    @NotBlank
    private Integer cancellationId;

    @ApiModelProperty("发起用户id")
    @NotBlank
    private Integer userId;

    @ApiModelProperty("发起用户名")
    @NotNull
    @Length(min = 3, max = 20)
    private String username;

    @ApiModelProperty("请求发起时间")
    @Past
    private Date cancellationCreateTime;

    @ApiModelProperty("处理时间")
    @Past
    private Date handleTime;

    @ApiModelProperty("处理者用户名")
    @Length(min = 3, max = 20)
    private String handler;

    @ApiModelProperty("处理结果")
    @NotBlank
    private Integer result;

}
