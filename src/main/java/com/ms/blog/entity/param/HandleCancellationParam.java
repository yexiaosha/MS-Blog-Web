package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 处理注销请求表单
 * @author wyh
 * @date 2023/01/13 19:20
 */
@Data
@ApiModel("处理注销请求表单")
public class HandleCancellationParam {
    private Integer id;
    private Integer result;
    private String resultSuggest;
}
