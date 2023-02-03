package com.ms.blog.common.base;

import com.ms.blog.common.ExcelCheckResult;
import com.ms.blog.entity.ExcelCheckErr;
import java.io.IOException;
import java.util.List;

/**
 * 非正则表达式校验类基础类
 * @author wyh
 * @date 2023/02/03 11:44
 */
public abstract class BaseNonRegularExpressionValidator<T> {

    /**
     * 非正则校验
     * @param list 校验对象
     * @return 校验结果
     */
    public abstract ExcelCheckResult<T> nonValidateData(List<T> list);

    /**
     * 存入数据
     * @param list 存入表
     */
    public abstract void saveData(List<T> list);

    /**
     * 输出错误表格文件
     * @param errList 错误表
     * @param sign 文件数量
     * @throws IOException 输入输出流错误
     */
    public abstract void outPutErrExcel(List<ExcelCheckErr<T>> errList, int sign)throws IOException;
}
