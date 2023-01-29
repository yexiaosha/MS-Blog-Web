package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 文章检索字段类
 * @author wyh
 * @date 2023/01/06 15:13
 */

@Data
@ApiModel("文章检索字段")
public class ArticleConditionParam extends PageParam {
    private Integer isSecret;
    private Integer isStick;
    private String articleWriter;
    private Integer isPublish;
    private Integer isOriginal;
}
