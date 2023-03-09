package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 子评论参数类
 * @author wyh
 * @date 2023/02/20 15:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("子评论")
public class ChildCommentParam extends PageParam {
    @ApiModelProperty("父评论id")
    private Integer parentId;
}
