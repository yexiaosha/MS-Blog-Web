package com.ms.blog.controller;

import com.ms.blog.common.PageData;
import com.ms.blog.common.PageParam;
import com.ms.blog.common.Result;
import com.ms.blog.common.UserThreadLocal;
import com.ms.blog.common.annotation.ControllerLog;
import com.ms.blog.entity.param.ChildCommentParam;
import com.ms.blog.entity.param.CommentParam;
import com.ms.blog.entity.param.CommentSearchParam;
import com.ms.blog.entity.vo.CommentVo;
import com.ms.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * 评论接口
 * @author wyh
 * @date 2023/01/06 13:58
 */
@RestController
@Tag(name = "评论接口")
@RequestMapping("/msblog/comment")
@CrossOrigin
public class CommentController {

    @Resource
    private CommentService commentService;

    @Operation(description = "查找评论")
    @GetMapping("/list")
    @ControllerLog("查找评论")
    @Parameters({
            @Parameter(name = "articleId", description = "文章id"),
            @Parameter(name = "pageNo", description = "当前页码"),
            @Parameter(name = "pageSize", description = "每页大小")
    })
    public Result<PageData<CommentVo>> getCommentList(@RequestParam Integer articleId, @RequestParam Integer pageNo, @RequestParam Integer pageSize){
        PageParam pageParam = new PageParam();
        pageParam.setPageSize(pageSize);
        pageParam.setCurrentPage(pageNo);
        return commentService.getCommentList(articleId, pageParam);
    }

    @PostMapping("/search")
    @Operation(description = "搜索获取评论表单")
    @ControllerLog("搜索获取评论表单")
    public Result<PageData<CommentVo>> searchCommentList(@RequestBody CommentSearchParam commentSearchParam){
        return commentService.searchCommentList(commentSearchParam);
    }

    @Operation(description = "删除评论")
    @DeleteMapping("/delete/{id}")
    @ControllerLog("删除评论")
    @Parameters(
            @Parameter( name = "评论id", description = "id")
    )
    public Result<Integer> deleteComment(@PathVariable("id") Integer id){
        return commentService.deleteComment(id);
    }

    @Operation(description = "插入评论")
    @PostMapping("/insert")
    @ControllerLog("插入评论")
    public Result<Integer> insertComment(@RequestBody CommentParam commentParam){
        Integer userId = UserThreadLocal.get().getId();
        commentParam.setUserId(userId);
        return commentService.insertComment(commentParam);
    }

    @GetMapping("/child")
    @Operation(description = "获取子评论")
    @ControllerLog("获取子评论")
    public Result<PageData<CommentVo>> getChildCommentList(@RequestBody ChildCommentParam commentParam){
        return commentService.getChildCommentList(commentParam);
    }
}
