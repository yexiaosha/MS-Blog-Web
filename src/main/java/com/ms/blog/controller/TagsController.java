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
 * 标签接口
 * @author wyh
 * @date 2023/01/06 13:56
 */
@RestController
@Api(tags = "标签接口")
@RequestMapping("/msblog/tags")
public class TagsController {

    @GetMapping("/list")
    @ApiOperation("获取所有标签")
    public Result getTags(){
        return null;
    }

    @PostMapping("/search")
    @ApiOperation("查找所有标签")
    public Result searchTags(){
        return null;
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除标签")
    public Result deleteTags(){
        return null;
    }

    @PostMapping("/update")
    @ApiOperation("更改标签")
    public Result updateTag(){
        return null;
    }
}
