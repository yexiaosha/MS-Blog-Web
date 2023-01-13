package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 用户注销表单
 * @author wyh
 * @date 2023/01/13 16:33
 */
@Data
@ApiModel("用户注销表单")
public class CancellationParam {
    private String username;
    private String email;
    private String reasonText;
    private PageParam pageParam;
}
