package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 反馈搜索表单类
 * @author wyh
 * @date 2023/02/13 10:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FeedbackSearchParam extends PageParam {
    private Integer type;
}
