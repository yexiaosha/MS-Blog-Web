package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 评论搜索参数类
 *
 * @author gary
 * @date 2023/4/6 19:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("评论搜索参数类")
public class CommentSearchParam extends PageParam {

    @ApiModelProperty("创建时间")
    private Date createTime;
}
