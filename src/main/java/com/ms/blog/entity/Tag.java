package com.ms.blog.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签
 * @author wyh
 * @date 2023/01/09 18:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tag {

    /**
     * 主键id
     */
    private Integer id;

    /**
     *  标签名称
     */
    private String name;

    /**
     * 点击量
     */
    private Integer clickVolume;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
