package com.ms.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.common.PageData;
import com.ms.blog.common.PageParam;
import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ServiceLog;
import com.ms.blog.dao.CommentMapper;
import com.ms.blog.entity.Comment;
import com.ms.blog.entity.param.ChildCommentParam;
import com.ms.blog.entity.param.CommentParam;
import com.ms.blog.entity.param.CommentSearchParam;
import com.ms.blog.entity.vo.CommentVo;
import com.ms.blog.service.CommentService;
import com.ms.blog.service.UserService;
import com.ms.blog.util.ResultUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论业务接口实现
 * @author wyh
 * @date 2023/02/20 14:09
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private UserService userService;

    @Override
    @ServiceLog("获取文章评论列表")
    public Result<PageData<CommentVo>> getCommentList(Integer articleId, PageParam pageParam) {
        Page<Comment> commentPage = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        IPage<Comment> commentIPage = commentMapper.getParentCommentList(articleId, commentPage);
        List<Comment> commentList = commentIPage.getRecords();
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment c : commentList) {
            CommentVo commentVo = copy(c);
            commentVoList.add(commentVo);
        }
        ChildCommentParam childCommentParam = new ChildCommentParam();
        childCommentParam.setPageSize(3);
        for (CommentVo c : commentVoList) {
            childCommentParam.setParentId(c.getId());
            List<CommentVo> commentChildVos = getChildCommentList(childCommentParam).getData().getPageData();
            c.setChildCommentList(commentChildVos);
        }
        PageData<CommentVo> commentVoPageData = new PageData<>(commentVoList, commentIPage.getTotal(), commentIPage.getPages(), commentIPage.getCurrent());
        return ResultUtils.success(commentVoPageData);
    }

    @Override
    @ServiceLog("删除评论")
    public Result<Integer> deleteComment(Integer id) {
        commentMapper.deleteComment(id);
        return ResultUtils.success();
    }

    @Override
    @ServiceLog("新增评论")
    public Result<Integer> insertComment(CommentParam commentParam) {
        Comment comment = Comment.builder()
                .articleId(commentParam.getArticleId())
                .content(commentParam.getContent())
                .createTime(new Date())
                .userId(commentParam.getUserId())
                .replyUserId(commentParam.getReplyUserId())
                .parentId(commentParam.getParentId())
                .build();
        if (comment.getParentId() != null){
            commentMapper.insertChildComment(comment);
        }else {
            commentMapper.insertParentComment(comment);
        }
        return ResultUtils.success();
    }

    @Override
    @ServiceLog("获取子评论")
    public Result<PageData<CommentVo>> getChildCommentList(ChildCommentParam childCommentParam) {
        Page<Comment> commentPage = new Page<>(childCommentParam.getCurrentPage(), childCommentParam.getPageSize());
        IPage<Comment> childCommentList = commentMapper.getChildCommentList(childCommentParam.getParentId(), commentPage);
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment c : childCommentList.getRecords()) {
            CommentVo commentVo = copy(c);
            commentVoList.add(commentVo);
        }

        PageData<CommentVo> commentVoPageData = new PageData<>(commentVoList, childCommentList.getTotal(), childCommentList.getPages(), childCommentList.getCurrent());
        return ResultUtils.success(commentVoPageData);
    }

    @Override
    @ServiceLog("获取评论表单")
    public Result<PageData<CommentVo>> searchCommentList(CommentSearchParam commentSearchParam) {
        Page<Comment> commentPage = new Page<>(commentSearchParam.getCurrentPage(), commentSearchParam.getPageSize());
        IPage<Comment> commentListIPage = commentMapper.searchCommentList(commentSearchParam, commentPage);
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment c : commentListIPage.getRecords()) {
            CommentVo commentVo = copy(c);
            commentVoList.add(commentVo);
        }
        PageData<CommentVo> commentVoPageData = new PageData<>(commentVoList, commentListIPage.getTotal(), commentListIPage.getPages(), commentListIPage.getCurrent());
        return ResultUtils.success(commentVoPageData);
    }

    private CommentVo copy(Comment comment){
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);
        commentVo.setNickname(userService.getUserInfoDetailsByUserId(comment.getUserId()).getNickname());
        return commentVo;
    }


}
