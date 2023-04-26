package com.ms.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.entity.Article;
import com.ms.blog.entity.Comment;
import com.ms.blog.entity.param.CommentSearchParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 评论接口
 * @author wyh
 * @date 2023/02/20 14:17
 */
@Mapper
public interface CommentMapper extends BaseMapper<Article> {

    /**
     * 获取父评论
     * @param articleId 文章id
     * @param page 分页参数
     * @return 结果
     */
    IPage<Comment> getParentCommentList(@Param("articleId") Integer articleId, Page<Comment> page);

    /**
     * 获取子评论
     * @param parentId 父评论id
     * @param page 分页参数
     * @return 结果
     */
    IPage<Comment> getChildCommentList(@Param("parentId") Integer parentId, Page<Comment> page);

    /**
     * 删除评论
     * @param id 评论id
     * @return  删除评论
     */
    int deleteComment(@Param("id") Integer id);

    /**
     * 插入子评论
     * @param comment 评论类
     * @return 结果
     */
    int insertChildComment(Comment comment);

    /**
     * 插入父评论
     * @param comment 评论类
     * @return 结果
     */
    int insertParentComment(Comment comment);

    /**
     * 获取评论表单
     * @param commentSearchParam 搜索参数类
     * @param commentPage 分页参数
     * @return 结果
     */
    IPage<Comment> searchCommentList(@Param("commentSearchParam") CommentSearchParam commentSearchParam, Page<Comment> commentPage);
}
