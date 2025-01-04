package com.ms.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 文章视图类
 * @author wyh
 * @date 2023/01/16 10:37
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "文章视图类")
public class ArticleVo {
    /**
     * 主键id
     */
    @Schema(name = "主键id")
    private Integer id;

    /**
     * 用户id
     */
    @Schema(name = "用户id")
    private Integer userId;

    @Schema(name = "用户昵称")
    private String userNickname;

    /**
     * 分类
     */
    @Schema(name = "分类")
    private CategoryVo categoryVo;

    /**
     *  文章标题
     */
    @Schema(name = "文章标题")
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
    private String summary;

    /**
     * 文章内容
     */
    @Schema(name = "文章内容")
    private String content;

    /**
     * 文章内容md版
     */
    @Schema(name = "文章内容md版")
    private String contentMd;

    /**
     * 是否私密
     */
    @Schema(name = "是否私密")
    private Integer isSecret;

    /**
     * 是否顶置
     */
    @Schema(name = "是否顶置")
    private Integer isStick;

    /**
     * 是否发布
     */
    @Schema(name = "是否发布")
    private Integer isPublish;

    /**
     * 是否原创
     */
    @Schema(name = "是否原创")
    private Integer isOriginal;

    /**
     * 转载地址
     */
    @Schema(name = "转载地址")
    private String originalUrl;

    /**
     * 文章阅读量
     */
    @Schema(name = "文章阅读量")
    private Integer quantity;

    /**
     * 说明
     */
    @Schema(name = "说明")
    private String remark;

    /**
     * 创建时间
     */
    @Schema(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     * 关键词
     */
    @Schema(name = "关键词")
    private String keyword;

    /**
     * 修改时间
     */
    @Schema(name = "修改时间")
    private Date updateDate;

    /**
     * 点赞数
     */
    @Schema(name = "点赞数")
    private Integer likeCount;

    @Schema(name = "评论")
    private List<CommentVo> commentVoList;

    @Schema(name = "评论数量")
    private Integer commentCount;

    @Schema(name = "上一篇文章")
    private ArticleSimpleVo lastArticle;

    @Schema(name = "下一篇文章")
    private ArticleSimpleVo nextArticle;

    @Schema(name = "最新文章列表")
    private List<ArticleSimpleVo> newestArticleList;

    @Schema(name = "推荐文章")
    private List<ArticleSimpleVo> recommendArticleList;

    /**
     * 文章标签
     */
    @Schema(name = "文章标签")
    private List<TagVo> tagsVo;
}
