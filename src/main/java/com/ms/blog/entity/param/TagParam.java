package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 标签参数类
 * @author wyh
 * @date 2023/02/08 11:45
 */

@Data
@ApiModel("标签参数类")
public class TagParam {
    @ApiModelProperty("分类名")
    private String name;
}
