package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文章下架参数类
 *
 * @author gary
 * @date 2023/4/7 15:50
 */
@Data
@ApiModel("文章下架参数类")
public class ArticleTakeDownParam {

    @ApiModelProperty("文章id")
    private Integer articleId;

    @ApiModelProperty("下架理由")
    private String reason;
}
