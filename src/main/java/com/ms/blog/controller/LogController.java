package com.ms.blog.controller;

import com.ms.blog.common.Result;
import com.ms.blog.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志接口
 * @author wyh
 * @date 2023/01/09 17:57
 */
@RestController
@Api(tags = "日志接口")
public class LogController {

    @Resource
    private LogService logService;

    @ApiOperation("获取所有日志列表")
    @GetMapping("/list")
    public Result getLogs(){
        return null;
    }

    @ApiOperation("搜索日志记录")
    @PostMapping("/search")
    public Result searchLogs(){
        return null;
    }

    @ApiOperation("删除日志记录")
    @DeleteMapping("/delete")
    public Result deleteLog(){
        return null;
    }
}
