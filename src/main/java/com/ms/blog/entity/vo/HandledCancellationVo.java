package com.ms.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 已处理注销请求
 * @author wyh
 * @date 2023/01/13 18:09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("已处理注销请求")
public class HandledCancellationVo {
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
