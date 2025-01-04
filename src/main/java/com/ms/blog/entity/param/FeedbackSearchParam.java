package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 反馈搜索表单类
 * @author wyh
 * @date 2023/02/13 10:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "反馈搜索表单类")
public class FeedbackSearchParam extends PageParam {
    @Schema(name = "反馈类型 0需求 1缺陷")
    private Integer type;
}
