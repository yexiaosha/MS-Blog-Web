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
 * 评论接口
 * @author wyh
 * @date 2023/01/06 13:58
 */
@RestController
@Api(tags = "评论接口")
@RequestMapping("/msblog/comment")
public class CommentController {

    @ApiOperation("查找所有评论")
    @GetMapping("/List")
    public Result getComments(){
        return null;
    }

    @ApiOperation("条件搜索")
    @PostMapping("/search")
    public Result searchComments(){
        return null;
    }

    @ApiOperation("删除评论")
    @DeleteMapping("/delete")
    public Result deleteComment(){
        return null;
    }

    @ApiOperation("插入评论")
    @PostMapping("/insert")
    public Result insertComment(){
        return null;
    }

    @ApiOperation("修改评论")
    @PostMapping("/update")
    public Result updateComment(){
        return null;
    }
}
