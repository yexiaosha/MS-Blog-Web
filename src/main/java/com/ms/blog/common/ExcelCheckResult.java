package com.ms.blog.common;

import com.ms.blog.entity.ExcelCheckErr;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * excel数据导入结果
 * @author wyh
 * @date 2023/02/02 17:51
 */
@Data
@AllArgsConstructor
public class ExcelCheckResult<T> {
    private List<T> successes;
    private List<ExcelCheckErr<T>> errs;
}
