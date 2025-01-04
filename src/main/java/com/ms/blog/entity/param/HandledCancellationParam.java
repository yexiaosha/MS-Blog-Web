package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * 已处理注销请求表单
 * @author wyh
 * @date 2023/01/13 19:41
 */
@Data
@Schema(name = "已处理的注销申请")
@EqualsAndHashCode(callSuper = true)
public class HandledCancellationParam extends PageParam {

    @Schema(name = "注销请求id")
    @NotBlank
    private Integer cancellationId;

    @Schema(name = "发起用户id")
    @NotBlank
    private Integer userId;

    @Schema(name = "用户名")
    @NotNull
    @Length(min = 3, max = 20)
    private String username;

    @Schema(name = "请求发起时间")
    @Past
    private Date cancellationCreateTime;

    @Schema(name = "处理时间")
    @Past
    private Date handleTime;

    @Schema(name = "处理者用户名")
    @Length(min = 3, max = 20)
    private String handlerName;

    @Schema(name = "处理结果")
    @NotBlank
    private Integer result;

}
