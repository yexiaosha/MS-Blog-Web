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
 * 友情链接接口
 * @author wyh
 * @date 2023/01/05 18:10
 */
@RestController
@Api(tags = "友情链接接口")
@RequestMapping("/msblog/links")
public class LinksController {

    @GetMapping("/list")
    @ApiOperation("获取所有友情链接")
    public Result getLinks(){
        return null;
    }

    @PostMapping("/insert")
    @ApiOperation("新增友情链接")
    public Result insertLinks(){
        return null;
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除链接")
    public Result deleteLinks(){
        return null;
    }

    @PostMapping("/input")
    @ApiOperation("批量导入友情链接")
    public Result inputLinks(){
        return null;
    }
}
