package com.ms.blog.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.entity.ExceptionLog;
import com.ms.blog.entity.UserLog;
import com.ms.blog.entity.param.ExceptionLogParam;
import com.ms.blog.entity.param.UserLogParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 获取用户日志列表
     * @param param 参数类
     * @param page 分页参数
     * @return 分类列表
     */
    IPage<UserLog> getUserLogs(UserLogParam param, Page<UserLog> page);

    /**
     * 删除日志记录
     * @param id 日志id
     * @return 结果
     */
    int deleteUserLogById(@Param("id") Integer id);

    /**
     * 删除错误日志
     * @param id 日志id
     * @return 结果
     */
    int deleteExceptionLog(@Param("id") Integer id);

    /**
     * 获取错误日志
     * @param exceptionLogParam 错误日志参数
     * @param exceptionLogPage 分页参数
     * @return 结果
     */
    IPage<ExceptionLog> getExceptionLogs(@Param("exceptionLogParam") ExceptionLogParam exceptionLogParam, Page<ExceptionLog> exceptionLogPage);
}
