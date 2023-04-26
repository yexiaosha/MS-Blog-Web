package com.ms.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 反馈视图类
 * @author wyh
 * @date 2023/02/13 10:37
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("反馈视图类")
public class FeedbackVo {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("题目")
    private String title;

    @ApiModelProperty("反馈内容")
    private String content;

    @ApiModelProperty("反馈类型")
    private Integer type;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
