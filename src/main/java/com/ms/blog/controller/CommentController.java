package com.ms.blog.controller;

import com.ms.blog.common.PageData;
import com.ms.blog.common.PageParam;
import com.ms.blog.common.Result;
import com.ms.blog.common.UserThreadLocal;
import com.ms.blog.common.annotation.ControllerLog;
import com.ms.blog.entity.param.ChildCommentParam;
import com.ms.blog.entity.param.CommentParam;
import com.ms.blog.entity.vo.CommentVo;
import com.ms.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 评论接口
 * @author wyh
 * @date 2023/01/06 13:58
 */
@RestController
@Api(tags = "评论接口")
@RequestMapping("/msblog/comment")
@CrossOrigin
public class CommentController {

    @Resource
    private CommentService commentService;

    @ApiOperation("查找评论")
    @GetMapping("/list")
    @ControllerLog("查找评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章id"),
            @ApiImplicitParam(name = "pageNo", value = "当前页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小")
    })
    public Result<PageData<CommentVo>> getCommentList(@RequestParam Integer articleId, @RequestParam Integer pageNo, @RequestParam Integer pageSize){
        PageParam pageParam = new PageParam();
        pageParam.setPageSize(pageSize);
        pageParam.setCurrentPage(pageNo);
        return commentService.getCommentList(articleId, pageParam);
    }

    @ApiOperation("删除评论")
    @DeleteMapping("/delete/{id}")
    @ControllerLog("删除评论")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "评论id", value = "id")
    )
    public Result<Integer> deleteComment(@PathVariable("id") Integer id){
        return commentService.deleteComment(id);
    }

    @ApiOperation("插入评论")
    @PostMapping("/insert")
    @ControllerLog("插入评论")
    public Result<Integer> insertComment(@RequestBody CommentParam commentParam){
        Integer userId = UserThreadLocal.get().getId();
        commentParam.setUserId(userId);
        return commentService.insertComment(commentParam);
    }

    @GetMapping("/child")
    @ApiOperation("获取子评论")
    @ControllerLog("获取子评论")
    public Result<PageData<CommentVo>> getChildCommentList(@RequestBody ChildCommentParam commentParam){
        return commentService.getChildCommentList(commentParam);
    }
}
