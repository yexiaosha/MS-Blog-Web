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

    IPage<Comment> getParentCommentList(Integer articleId, Page<Comment> page);
    IPage<Comment> getChildCommentList(Integer parentId, Page<Comment> page);

    int deleteComment(Integer id);

    int insertChildComment(Comment comment);

    int insertParentComment(Comment comment);
}
