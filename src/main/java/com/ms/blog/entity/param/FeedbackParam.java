package com.ms.blog.entity.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 反馈表单类
 * @author wyh
 * @date 2023/02/13 10:40
 */
@Data
@Schema(name = "反馈表单类")
public class FeedbackParam {

    @Schema(name = "邮箱")
    private String email;

    @Schema(name = "题目")
    private String title;

    @Schema(name = "内容")
    private String content;

    @Schema(name = "类型")
    private Integer type;
}
