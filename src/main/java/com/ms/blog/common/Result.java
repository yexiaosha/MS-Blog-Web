package com.ms.blog.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用结果类
 * @author wyh
 * @date 2023/01/06 14:53
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name ="通用返回类")
public class Result<T> {

    @Schema(name ="状态 0失败 1成功")
    private Integer status;

    @Schema(name ="状态码")
    private String code;

    @Schema(name ="返回消息")
    private String message;

    @Schema(name ="返回数据")
    private T data;
}
