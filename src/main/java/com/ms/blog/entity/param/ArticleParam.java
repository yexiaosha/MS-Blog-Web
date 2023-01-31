package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 文章参数类
 * @author wyh
 * @date 2023/01/06 16:26
 */

@Data
@ApiModel("文章参数类")
public class ArticleParam{

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    @NotNull
    private Integer userId;

    /**
     * 分类
     */
    @ApiModelProperty("分类")
    @NotNull
    @Length(min = 1, max = 10)
    private String category;

    /**
     *  文章标题
     */
    @ApiModelProperty("文章标题")
    @NotNull
    @Length(min = 4, max = 30, message = "标题长度必须在4个字符以上30个字符以内")
    private String title;

    /**
     * 文章封面地址
     */
    @ApiModelProperty("文章封面地址")
    private String avatar;

    /**
     * 文章简介
     */
    @ApiModelProperty("文章简介")
    @NotNull
    @Length(min = 10, max = 100, message = "文章简介必须在10个字符以上100个字符以内")
    private String summary;

    /**
     * 文章内容
     */
    @ApiModelProperty("文章内容")
    @NotNull
    @Length(min = 50, message = "Markdown文本必须大于50个字符")
    private String content;

    /**
     * 文章内容md版
     */
    @ApiModelProperty("文章内容md版")
    @NotNull
    @Length(min = 50, message = "Markdown文本必须大于50个字符")
    private String contentMd;

    /**
     * 是否私密
     */
    @ApiModelProperty("是否私密")
    @NotBlank
    private Integer isSecret;

    /**
     * 是否顶置
     */
    @ApiModelProperty("是否顶置")
    @NotBlank
    private Integer isStick;

    /**
     * 是否原创
     */
    @ApiModelProperty("是否原创")
    @NotBlank
    private Integer isOriginal;

    /**
     * 转载地址
     */
    @ApiModelProperty("转载地址")
    private String originalUrl;

    /**
     * 说明
     */
    @ApiModelProperty("说明")
    @NotBlank
    @Length(min = 5, max = 20)
    private String remark;

}
