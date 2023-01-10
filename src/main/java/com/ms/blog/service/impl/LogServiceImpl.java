package com.ms.blog.service.impl;

import com.ms.blog.common.Result;
import com.ms.blog.entity.ExceptionLog;
import com.ms.blog.entity.UserLog;
import com.ms.blog.service.LogService;
import org.springframework.stereotype.Service;

/**
 * 日志业务实现类
 * @author wyh
 * @date 2023/01/10 14:42
 */
@Service
public class LogServiceImpl implements LogService {

    @Override
    public Result insertUserLog(UserLog userLog) {
        return null;
    }

    @Override
    public Result insertExceptionLog(ExceptionLog exceptionLog) {
        return null;
    }
}
