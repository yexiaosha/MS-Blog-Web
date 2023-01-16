package com.ms.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章标签视图类
 * @author wyh
 * @date 2023/01/16 14:12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("文章标签视图类")
public class TagVo {
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
