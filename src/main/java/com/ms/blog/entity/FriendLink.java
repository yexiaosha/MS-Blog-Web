package com.ms.blog.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 友情链接
 * @author wyh
 * @date 2023/01/09 18:24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FriendLink {

    /**
     * 主键id
     */
    @ExcelIgnore
    private Integer id;

    /**
     *  网站名称
     */
    @ExcelProperty("网站名称")
    private String name;

    /**
     *  网站链接
     */
    @ExcelProperty("网站链接")
    private String url;

    /**
     * 网站头像地址
     */
    @ExcelProperty("网站头像")
    private String avatar;

    /**
     * 网站信息
     */
    @ExcelProperty("网站信息")
    private String info;

    /**
     * 邮箱
     */
    @ExcelProperty("邮箱")
    private String email;

    /**
     *  下架原因
     */
    @ExcelIgnore
    private String reason;

    /**
     * 排序
     */
    @ExcelIgnore
    private Integer sort;

    /**
     * 状态  0待审核 1通过
     */
    @ExcelIgnore
    private Integer status;

    /**
     * 创建时间
     */
    @ExcelIgnore
    private Date creatTime;

    /**
     *  更新时间
     */
    @ExcelIgnore
    private Date updateTime;
}
