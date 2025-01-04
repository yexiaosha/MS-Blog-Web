package com.ms.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "反馈视图类")
public class FeedbackVo {

    @Schema(name ="主键id")
    private Integer id;

    @Schema(name ="邮箱")
    private String email;

    @Schema(name ="题目")
    private String title;

    @Schema(name ="反馈内容")
    private String content;

    @Schema(name ="反馈类型")
    private Integer type;

    @Schema(name ="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
