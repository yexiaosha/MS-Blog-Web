package com.ms.blog.entity.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 处理注销请求表单
 * @author wyh
 * @date 2023/01/13 19:20
 */
@Data
@Schema(name = "处理注销请求表单")
public class HandleCancellationParam {

    @Schema(name = "注销请求id")
    @NotBlank
    private Integer cancellationId;

    @Schema(name = "求情处理结果 0驳回请求 1批准注销")
    @NotBlank
    private Integer result;

    @Schema(name = "结果建议")
    @Length(min = 2, max = 100, message = "结果建议必须在2个字符以上，100个字符以内")
    @NotNull
    private String resultSuggest;
}
