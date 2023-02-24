package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 评论参数类
 * @author wyh
 * @date 2023/02/15 09:40
 */
@Data
@ApiModel("评论参数类")
public class CommentParam {

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("文章id")
    private Integer articleId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("回复用户id")
    private Integer replyUserId;

    @ApiModelProperty("父评论id")
    private Integer parentId;
}
