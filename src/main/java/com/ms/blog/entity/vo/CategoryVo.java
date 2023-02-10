package com.ms.blog.entity.vo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类视图类
 * @author wyh
 * @date 2023/01/30 18:11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo {
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
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;
}
