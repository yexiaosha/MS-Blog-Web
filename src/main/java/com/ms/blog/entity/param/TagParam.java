package com.ms.blog.entity.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 标签参数类
 * @author wyh
 * @date 2023/02/08 11:45
 */

@Data
@Schema(name ="标签参数类")
public class TagParam {
    @Schema(name ="分类名")
    private String name;
}
