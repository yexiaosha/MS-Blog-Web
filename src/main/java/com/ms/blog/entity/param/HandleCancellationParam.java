package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 处理注销请求表单
 * @author wyh
 * @date 2023/01/13 19:20
 */
@Data
@ApiModel("处理注销请求表单")
public class HandleCancellationParam {

    @ApiModelProperty("注销请求id")
    @NotBlank
    private Integer cancellationId;

    @ApiModelProperty("求情处理结果 0驳回请求 1批准注销")
    @NotBlank
    private Integer result;

    @ApiModelProperty("结果建议")
    @Length(min = 2, max = 100, message = "结果建议必须在2个字符以上，100个字符以内")
    @NotNull
    private String resultSuggest;
}
