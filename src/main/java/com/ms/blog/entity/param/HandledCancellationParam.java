package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.annotations.ApiModel;
import java.util.Date;
import lombok.Data;

/**
 * 已处理注销请求表单
 * @author wyh
 * @date 2023/01/13 19:41
 */
@Data
@ApiModel("已处理注销请求表单")
public class HandledCancellationParam extends PageParam {
    private Integer id;
    private Integer cancellationId;
    private Integer userId;
    private Date cancellationCreateTime;
    private Date handleDate;
    private String handler;
    private Integer result;
    private String resultSuggest;
    private Integer handlerId;
}
