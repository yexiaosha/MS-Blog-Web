package com.ms.blog.entity;

import io.swagger.annotations.ApiModel;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章类
 * @author wyh
 * @date 2023/01/06 15:56
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
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
    private Boolean isSecret;

    /**
     * 是否顶置
     */
    private Boolean isStick;

    /**
     * 是否发布
     */
    private Boolean isPublish;

    /**
     * 是否原创
     */
    private Boolean isOriginal;

    /**
     * 转载地址
     */
    private String originalUrl;

    /**
     * 文章阅读量
     */
    private String quantity;

    /**
     * 说明
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 修改时间
     */
    private Date updateDate;
}
