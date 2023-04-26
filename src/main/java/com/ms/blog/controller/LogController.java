package com.ms.blog.controller;

import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.entity.param.ExceptionLogParam;
import com.ms.blog.entity.param.UserLogParam;
import com.ms.blog.entity.vo.ExceptionLogVo;
import com.ms.blog.entity.vo.UserLogVo;
import com.ms.blog.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 日志接口
 * @author wyh
 * @date 2023/01/09 17:57
 */
@RestController
@Api(tags = "日志接口")
@RequestMapping("/msblog/log")
@CrossOrigin
public class LogController {

    @Resource
    private LogService logService;

    @ApiOperation("获取用用户日志列表")
    @PostMapping("/user/list")
    public Result<PageData<UserLogVo>> getUserLogs(@RequestBody UserLogParam userLogParam){
        return logService.getUserLogs(userLogParam);
    }


    @ApiOperation("删除用户日志记录")
    @DeleteMapping("/delete/{id}")
    public Result<Integer> deleteLog(@PathVariable("id") Integer id){
        return logService.deleteUserLogById(id);
    }

    @ApiOperation("获取错误日志列表")
    @PostMapping("/exception/list")
    public Result<PageData<ExceptionLogVo>> getExceptionLogs(@RequestBody ExceptionLogParam exceptionLogParam){
        System.out.println(exceptionLogParam.toString());
        return logService.getExceptionLogs(exceptionLogParam);
    }

    @DeleteMapping("/exception/delete/{id}")
    @ApiOperation("删除错误日志")
    public Result<Integer> deleteExceptionLog(@PathVariable("id") Integer id){
        return logService.deleteExceptionLogById(id);

    }
}
