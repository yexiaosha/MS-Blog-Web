package com.ms.blog.controller;

import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.entity.param.UserLogParam;
import com.ms.blog.entity.vo.UserLogVo;
import com.ms.blog.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志接口
 * @author wyh
 * @date 2023/01/09 17:57
 */
@RestController
@Api(tags = "日志接口")
@RequestMapping("/msblog/log")
public class LogController {

    @Resource
    private LogService logService;

    @ApiOperation("获取用用户日志列表")
    @GetMapping("/user/list")
    public Result<PageData<UserLogVo>> getUserLogs(@RequestParam UserLogParam userLogParam){
        return logService.getUserLogs(userLogParam);
    }


    @ApiOperation("删除日志记录")
    @DeleteMapping("/delete")
    public Result deleteLog(){
        return null;
    }
}
