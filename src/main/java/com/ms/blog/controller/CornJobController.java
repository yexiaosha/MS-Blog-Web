package com.ms.blog.controller;

import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ControllerLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务接口
 * @author wyh
 * @date 2023/01/06 14:27
 */

@RestController
@Tag(name = "定时任务接口")
@RequestMapping("/msblog/task")
public class CornJobController {

    @GetMapping("/list")
    @Operation(description = "获取定时任务接口")
    @ControllerLog("获取定时任务接口")
    public Result getTaskList(){
        return null;
    }
}
