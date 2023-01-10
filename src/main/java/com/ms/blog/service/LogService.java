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
     *
     * @param userLog
     * @return
     */
    Result insertUserLog(UserLog userLog);

    /**
     *
     * @param exceptionLog
     * @return
     */
    Result insertExceptionLog(ExceptionLog exceptionLog);
}
