package com.ms.blog.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类
 * @author wyh
 * @date 2023/01/09 18:06
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    /**
     *  主键id
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 点击量
     */
    private Integer clickVolume;

    /**
     *  排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
