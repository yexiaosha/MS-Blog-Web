package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 分类参数类
 * @author wyh
 * @date 2023/02/10 16:06
 */
@Data
@ApiModel("分类参数类")
public class CategoryParam {
    private Integer id;
    private String name;
}
