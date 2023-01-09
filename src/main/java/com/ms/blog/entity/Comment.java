package com.ms.blog.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论
 * @author wyh
 * @date 2023/01/09 18:12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    /**
     *  主键id
     */
    private Integer id;

    /**
     * 评论者id
     */
    private Integer userId;

    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 文章内容
     */
    private String content;

    /**
     *  回复人id
     */
    private Integer replyUserId;

    /**
     * 父评论id
     */
    private Integer parentId;

    /**
     * 创建时间
     */
    private Date createTime;
}
