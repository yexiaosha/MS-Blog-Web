package com.ms.blog.controller;

import com.ms.blog.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 反馈接口
 * @author wyh
 * @date 2023/01/06 13:38
 */
@RestController
@Api(tags = "反馈接口")
@RequestMapping("/msblog/feedback")
public class FeedBackController {

    @ApiOperation("搜索反馈")
    @PostMapping("/search")
    public Result searchFeedBack(){
        return null;
    }

    @ApiOperation("获取所有反馈")
    @GetMapping("/list")
    public Result getFeedBack(){
        return null;
    }

    @ApiOperation("删除反馈")
    @DeleteMapping("/delete")
    public Result deleteFeedBack(){
        return null;
    }

    @ApiOperation("导出反馈")
    @PostMapping("/output")
    public Result outputExcelFile(){
        return null;
    }
}
