package com.ms.blog.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户反馈
 * @author wyh
 * @date 2023/01/09 18:18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedBack {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 图片链接
     */
    private String imgUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     *  反馈类型 0需求 1缺陷
     */
    private Integer type;
}
