package com.ms.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 错误内容
 * @author wyh
 * @date 2023/02/02 17:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelCheckErr<T> {
    private T t;
    private String errMessage;
}
