package com.ms.blog.dao;

import com.ms.blog.entity.ExceptionLog;
import com.ms.blog.entity.UserLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日志接口
 * @author wyh
 * @date 2023/03/08 09:07
 */
@Mapper
public interface LogMapper {

    /**
     * 插入用户日志
     * @param userLog 用户日志类
     * @return 结果
     */
    int insertUserLog(UserLog userLog);

    /**
     * 插入错误日志
     * @param exceptionLog 错误日志
     * @return 结果
     */
    int insertExceptionLog(ExceptionLog exceptionLog);
}
