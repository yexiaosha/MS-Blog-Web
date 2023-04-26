package com.ms.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("评论视图类")
public class CommentVo {
    /**
     *  主键id
     */
    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("用户头像")
    private Integer avatar;

    /**
     * 评论者别称
     */
    @ApiModelProperty("评论者别称")
    private String nickname;

    /**
     * 文章id
     */
    @ApiModelProperty("文章id")
    private Integer articleId;

    /**
     * 评论内容
     */
    @ApiModelProperty("评论内容")
    private String content;

    /**
     * 父评论id
     */
    @ApiModelProperty("父评论id")
    private Integer parentId;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     * 子评论
     */
    @ApiModelProperty("子评论")
    private List<CommentVo> childCommentList;
}
