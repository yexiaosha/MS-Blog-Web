package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 反馈搜索表单类
 * @author wyh
 * @date 2023/02/13 10:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("反馈搜索表单类")
public class FeedbackSearchParam extends PageParam {
    @ApiModelProperty("反馈类型 0需求 1缺陷")
    private Integer type;
}
