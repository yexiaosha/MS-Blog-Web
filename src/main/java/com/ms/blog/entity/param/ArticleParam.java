package com.ms.blog.entity.param;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 文章参数类
 * @author wyh
 * @date 2023/01/06 16:26
 */

@Data
@Schema(name = "文章参数类")
public class ArticleParam{

    /**
     * 文章id
     */
    @Schema(name = "文章id")
    private Integer id;
    /**
     * 用户id
     */
    @Schema(name = "用户id")
    @NotNull
    private Integer userId;

    /**
     * 分类
     */
    @Schema(name = "分类")
    @NotNull
    @Length(min = 1, max = 10)
    private String category;

    /**
     *  文章标题
     */
    @Schema(name = "文章标题")
    @NotNull
    @Length(min = 4, max = 30, message = "标题长度必须在4个字符以上30个字符以内")
    private String title;

    /**
     * 文章封面地址
     */
    @Schema(name = "文章封面地址")
    private String avatar;

    /**
     * 文章简介
     */
    @Schema(name = "文章简介")
    @NotNull
    @Length(min = 10, max = 100, message = "文章简介必须在10个字符以上100个字符以内")
    private String summary;

    /**
     * 文章内容
     */
    @Schema(name = "文章内容")
    @NotNull
    @Length(min = 50, message = "Markdown文本必须大于50个字符")
    private String content;

    /**
     * 文章内容md版
     */
    @Schema(name = "文章内容md版")
    @NotNull
    @Length(min = 50, message = "Markdown文本必须大于50个字符")
    private String contentMd;

    /**
     * 是否私密
     */
    @Schema(name = "是否私密")
    @NotBlank
    private Integer isSecret;

    /**
     * 是否顶置
     */
    @Schema(name = "是否顶置")
    @NotBlank
    private Integer isStick;

    /**
     * 是否原创
     */
    @Schema(name = "是否原创")
    @NotBlank
    private Integer isOriginal;

    /**
     * 转载地址
     */
    @Schema(name = "转载地址")
    private String originalUrl;

    /**
     * 说明
     */
    @Schema(name = "说明")
    @NotBlank
    @Length(min = 5, max = 20)
    private String remark;

    @Schema(name = "标签列表")
    @NotEmpty
    @Size(min = 1, max = 4, message = "文章标签必须在1个以上4个及4个以内")
    private List<String> tagList;
}
