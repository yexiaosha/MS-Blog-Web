package com.ms.blog.entity.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分类参数类
 * @author wyh
 * @date 2023/02/10 16:06
 */
@Data
@Schema(name = "分类参数类")
public class CategoryParam {

    @Schema(name = "分类id")
    private Integer id;

    @Schema(name = "分类名")
    private String name;
}
