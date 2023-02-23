package com.ms.blog.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论接口
 * @author wyh
 * @date 2023/02/20 14:17
 */
@Mapper
public interface CommentMapper {

    /**
     * 获取父评论
     * @param articleId 文章id
     * @param page 分页参数
     * @return 结果
     */
    IPage<Comment> getParentCommentList(Integer articleId, Page<Comment> page);

    /**
     * 获取子评论
     * @param parentId 父评论id
     * @param page 分页参数
     * @return 结果
     */
    IPage<Comment> getChildCommentList(Integer parentId, Page<Comment> page);

    /**
     * 删除评论
     * @param id 评论id
     * @return  删除评论
     */
    int deleteComment(Integer id);

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
}
