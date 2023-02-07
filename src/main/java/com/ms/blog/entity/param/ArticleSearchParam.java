package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
@ApiModel("文章检索字段")
public class ArticleSearchParam extends PageParam {

    @ApiModelProperty("是否私密文章")
    @Min(0)
    @Max(1)
    private Integer isSecret;

    @ApiModelProperty("是否顶置")
    @Min(0)
    @Max(1)
    private Integer isStick;

    @ApiModelProperty("文章作者")
    @Length(min = 3, max = 20,message = "作者名称必须大于等于3小于等于20个字符")
    private String articleWriter;

    @ApiModelProperty("是否发布")
    @Min(0)
    @Max(1)
    private Integer isPublish;

    @ApiModelProperty("是否原创")
    @Min(0)
    @Max(1)
    private Integer isOriginal;

    @ApiModelProperty("分类名")
    @Length(min = 1, max = 10, message ="分类名必须大于等于1小于等于10个字符" )
    private String categoryName;

    @ApiModelProperty("文章标签名")
    @Length(min = 1, max = 10, message ="文章标签名必须大于等于1小于等于10个字符")
    private String tagName;
}
