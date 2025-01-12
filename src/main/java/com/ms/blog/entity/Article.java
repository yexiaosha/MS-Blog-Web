package com.ms.blog.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 文章类
 * @author wyh
 * @date 2023/01/06 15:56
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "文章实体")
public class Article {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     *  文章标题
     */
    private String title;

    /**
     * 文章封面地址
     */
    private String avatar;

    /**
     * 文章简介
     */
    private String summary;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章内容md版
     */
    private String contentMd;

    /**
     * 是否私密
     */
    private Integer isSecret;

    /**
     * 是否顶置
     */
    private Integer isStick;

    /**
     * 是否发布
     */
    private Integer isPublish;

    /**
     * 是否原创
     */
    private Integer isOriginal;

    /**
     * 转载地址
     */
    private String originalUrl;

    /**
     * 文章阅读量
     */
    private Integer quantity;

    /**
     * 说明
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 点赞数
     */
    private Integer likeCount;
}
