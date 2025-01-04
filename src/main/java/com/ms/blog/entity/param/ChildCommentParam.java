package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 子评论参数类
 * @author wyh
 * @date 2023/02/20 15:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "子评论")
public class ChildCommentParam extends PageParam {
    @Schema(name = "父评论id")
    private Integer parentId;
}
