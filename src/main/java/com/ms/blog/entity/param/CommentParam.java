package com.ms.blog.entity.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 评论参数类
 * @author wyh
 * @date 2023/02/15 09:40
 */
@Data
@Schema(name = "评论参数类")
public class CommentParam {

    @Schema(name = "用户id")
    private Integer userId;

    @Schema(name = "文章id")
    private Integer articleId;

    @Schema(name = "内容")
    private String content;

    @Schema(name = "回复用户id")
    private Integer replyUserId;

    @Schema(name = "父评论id")
    private Integer parentId;
}
