package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 反馈表单类
 * @author wyh
 * @date 2023/02/13 10:40
 */
@Data
@ApiModel("反馈表单类")
public class FeedbackParam {

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("题目")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("类型")
    private Integer type;
}
