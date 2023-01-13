package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 查找用户表单
 * @author wyh
 * @date 2023/01/13 11:29
 */
@Data
@ApiModel("查找用户表单")
public class UserParam {
    private PageParam pageParam;
}
