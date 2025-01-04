package com.ms.blog.entity.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文章下架参数类
 *
 * @author gary
 * @date 2023/4/7 15:50
 */
@Data
@Schema(name ="文章下架参数类")
public class ArticleTakeDownParam {

    @Schema(name = "文章id")
    private Integer articleId;

    @Schema(name = "下架理由")
    private String reason;
}
