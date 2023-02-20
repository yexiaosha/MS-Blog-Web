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
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Resource
    private CommentService commentService;

    @ApiOperation("查找评论")
    @PostMapping("/list/{articleId}")
    @ControllerLog("查找评论")
    @ApiImplicitParam(name = "文章id", value = "articleId")
    public Result<PageData<CommentVo>> getCommentList(@PathVariable("articleId") Integer articleId, @RequestBody PageParam pageParam){
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
        return null;
    }
}
