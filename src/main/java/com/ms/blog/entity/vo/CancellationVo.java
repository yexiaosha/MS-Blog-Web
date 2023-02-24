package com.ms.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("理由")
    private String reasonText;
}
