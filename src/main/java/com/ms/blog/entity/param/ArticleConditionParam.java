package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 文章检索字段类
 * @author wyh
 * @date 2023/01/06 15:13
 */

@Data
@ApiModel("文章检索字段")
public class ArticleConditionParam {

    private String tag;
    private String category;
    private String articleWriter;

    @NotBlank
    @ApiModelProperty("查找关键字")
    private String condition;
}
