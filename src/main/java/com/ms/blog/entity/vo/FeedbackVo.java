package com.ms.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ApiModelProperty("文章内容")
    private String content;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
