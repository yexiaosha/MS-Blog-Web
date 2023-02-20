package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 子评论参数类
 * @author wyh
 * @date 2023/02/20 15:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChildCommentParam extends PageParam {
    private Integer parentId;
}
