package com.ms.blog.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 已处理的注销请求
 * @author wyh
 * @date 2023/01/13 16:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HandledCancellation {
    private Integer id;
    private Integer cancellationId;
    private Integer userId;
    private String username;
    private Date cancellationCreateTime;
    private Date handleDate;
    private String handlerName;
    private Integer result;
    private String resultSuggest;
    private Integer handlerId;
}
