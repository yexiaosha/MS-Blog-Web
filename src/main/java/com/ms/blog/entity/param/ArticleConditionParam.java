package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章检索字段类
 * @author wyh
 * @date 2023/01/06 15:13
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("文章检索字段")
public class ArticleConditionParam {

    @NotBlank
    @ApiModelProperty("查找条件")
    private String Condition;

    @NotBlank
    @ApiModelProperty("条件类型 0标签 1分类 2用户名")
    private Integer type;
}
