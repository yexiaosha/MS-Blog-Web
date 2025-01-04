package com.ms.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 评论视图类
 * @author wyh
 * @date 2023/02/15 09:47
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "评论视图类")
public class CommentVo {
    /**
     *  主键id
     */
    @Schema(name = "主键id")
    private Integer id;

    @Schema(name = "用户id")
    private Integer userId;

    @Schema(name = "用户头像")
    private Integer avatar;

    /**
     * 评论者别称
     */
    @Schema(name = "评论者别称")
    private String nickname;

    /**
     * 文章id
     */
    @Schema(name = "文章id")
    private Integer articleId;

    /**
     * 评论内容
     */
    @Schema(name = "评论内容")
    private String content;

    /**
     * 父评论id
     */
    @Schema(name = "父评论id")
    private Integer parentId;

    /**
     * 创建时间
     */
    @Schema(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     * 子评论
     */
    @Schema(name = "子评论")
    private List<CommentVo> childCommentList;
}
