package com.ms.blog.entity.vo;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论视图类
 * @author wyh
 * @date 2023/02/15 09:47
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    /**
     *  主键id
     */
    private Integer id;

    /**
     * 评论者别称
     */
    private String nikeName;

    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 评论内容
     */
    private String content;

    /**
     *  回复人别称
     */
    private Integer replyUserNikeName;

    /**
     * 父评论id
     */
    private Integer parentId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 子评论
     */
    private List<CommentVo> childCommentList;
}
