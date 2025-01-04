package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "评论搜索参数类")
public class CommentSearchParam extends PageParam {

    @Schema(name = "创建时间")
    private Date createTime;
}
