package com.ms.blog.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

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
    @ExcelProperty(value = "网站名称", index = 0)
    @NotBlank(message = "网站名字不能为空")
    private String name;

    /**
     *  网站链接
     */
    @ExcelProperty(value = "网站链接", index = 1)
    @NotBlank(message = "网站链接不能为空")
    private String url;

    /**
     * 网站头像地址
     */
    @ExcelIgnore
    private String avatar;

    /**
     * 网站信息
     */
    @ExcelProperty(value = "网站信息", index = 2)
    @Length(min = 10, max = 50, message = "长度必须在10个字符以上，50个字符以内")
    private String info;

    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱", index = 3)
    @Email
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
    private Date createTime;

    /**
     *  更新时间
     */
    @ExcelIgnore
    private Date updateTime;
}
