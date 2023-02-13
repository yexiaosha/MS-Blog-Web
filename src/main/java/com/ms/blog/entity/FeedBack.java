package com.ms.blog.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
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
public class FeedBack{
    /**
     * 主键id
     *
     */
    @ExcelProperty(value = "反馈id", index = 0)
    private Integer id;

    /**
     * 邮箱
     */
    @ExcelProperty("反馈者邮箱")
    @ColumnWidth(40)
    private String email;

    /**
     * 标题
     */
    @ExcelProperty("标题")
    @ColumnWidth(40)
    private String title;

    /**
     * 内容
     */
    @ExcelProperty("反馈内容")
    @ColumnWidth(40)
    private String content;

    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private Date createTime;

    /**
     *  反馈类型 0需求 1缺陷
     */
    @ExcelProperty(value = "反馈类型 0需求 1缺陷")
    private Integer type;
}
