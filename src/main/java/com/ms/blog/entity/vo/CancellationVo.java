package com.ms.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 注销请求表单视图类
 * @author wyh
 * @date 2023/01/13 17:31
 */
@Data
@ApiModel("注销请求表单视图类")
@Builder
@AllArgsConstructor
public class CancellationVo {
    private Integer id;
    private Integer userId;
    private String username;
    private String email;
    private Date createTime;
    private String reasonText;
}
