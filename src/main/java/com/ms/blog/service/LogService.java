package com.ms.blog.service;

import com.ms.blog.common.Result;
import com.ms.blog.entity.ExceptionLog;
import com.ms.blog.entity.UserLog;

/**
 * 日志业务接口
 * @author wyh
 * @date 2023/01/10 14:42
 */
public interface LogService {

    /**
     * 插入用户日志
     * @param userLog 用户日志对象
     * @return 结果
     */
    Result insertUserLog(UserLog userLog);

    /**
     * 插入错误日志
     * @param exceptionLog 错误日志
     * @return 结果
     */
    Result insertExceptionLog(ExceptionLog exceptionLog);
}
