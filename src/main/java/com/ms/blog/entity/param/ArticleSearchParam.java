package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * 文章检索字段类
 * @author wyh
 * @date 2023/01/06 15:13
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "文章检索字段")
public class ArticleSearchParam extends PageParam {

    @Schema(name = "是否私密文章")
    @Min(0)
    @Max(1)
    private Integer isSecret;

    @Schema(name = "是否顶置")
    @Min(0)
    @Max(1)
    private Integer isStick;

    @Schema(name = "文章作者")
    @Length(min = 3, max = 20,message = "作者名称必须大于等于3小于等于20个字符")
    private String articleWriter;

    @Schema(name = "文章标题")
    private String title;

    @Schema(name = "是否发布")
    @Min(0)
    @Max(1)
    private Integer isPublish;

    @Schema(name = "是否原创")
    @Min(0)
    @Max(1)
    private Integer isOriginal;

}
