package com.ms.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章视图类
 * @author wyh
 * @date 2023/01/16 10:37
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("文章视图类")
public class ArticleVo {
    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Integer userId;

    /**
     * 分类
     */
    @ApiModelProperty("分类")
    private CategoryVo categoryVo;

    /**
     *  文章标题
     */
    @ApiModelProperty("文章标题")
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
    private String summary;

    /**
     * 文章内容
     */
    @ApiModelProperty("文章内容")
    private String content;

    /**
     * 文章内容md版
     */
    @ApiModelProperty("文章内容md版")
    private String contentMd;

    /**
     * 是否私密
     */
    @ApiModelProperty("是否私密")
    private Integer isSecret;

    /**
     * 是否顶置
     */
    @ApiModelProperty("是否顶置")
    private Integer isStick;

    /**
     * 是否发布
     */
    @ApiModelProperty("是否发布")
    private Integer isPublish;

    /**
     * 是否原创
     */
    @ApiModelProperty("是否原创")
    private Integer isOriginal;

    /**
     * 转载地址
     */
    @ApiModelProperty("转载地址")
    private String originalUrl;

    /**
     * 文章阅读量
     */
    @ApiModelProperty("文章阅读量")
    private Integer quantity;

    /**
     * 说明
     */
    @ApiModelProperty("说明")
    private String remark;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String createTime;

    /**
     * 关键词
     */
    @ApiModelProperty("关键词")
    private String keyword;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private String updateDate;

    /**
     * 文章标签
     */
    @ApiModelProperty("文章标签")
    private List<TagVo> tagsVo;
}
