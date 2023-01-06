package com.ms.blog.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("通用返回类")
public class Result<T> {

    @ApiModelProperty("状态 0失败 1成功")
    private Integer status;

    @ApiModelProperty("状态码")
    private String code;

    @ApiModelProperty("返回消息")
    private String message;

    @ApiModelProperty("返回数据")
    private T data;
}
