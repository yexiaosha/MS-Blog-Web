package com.ms.blog.entity.param;

import lombok.Data;

/**
 * 评论参数类
 * @author wyh
 * @date 2023/02/15 09:40
 */
@Data
public class CommentParam {
    private Integer userId;
    private Integer articleId;
    private String content;
    private Integer replyUserId;
    private Integer parentId;
}
