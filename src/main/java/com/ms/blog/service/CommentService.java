package com.ms.blog.service;

import com.ms.blog.common.PageData;
import com.ms.blog.common.PageParam;
import com.ms.blog.common.Result;
import com.ms.blog.entity.param.ChildCommentParam;
import com.ms.blog.entity.param.CommentParam;
import com.ms.blog.entity.param.CommentSearchParam;
import com.ms.blog.entity.vo.CommentVo;

/**
* 评论业务类接口
* @author wyh
* @date 2023/02/20 13:45
*/

public interface CommentService {

    /**
     * 获取评论列表
     * @param pageParam 分页参数
     * @param articleId 文章id
     * @return  分页结果
     */
    Result<PageData<CommentVo>> getCommentList(Integer articleId ,PageParam pageParam);

    /**
     * 删除评论
     * @param id 评论id
     * @return 结果
     */
    Result<Integer> deleteComment(Integer id);

    /**
     * 新增评论
     * @param commentParam 评论表单
     * @return 结果
     */
    Result<Integer> insertComment(CommentParam commentParam);

    /**
     * 获取子评论
     * @param childCommentParam 父评论id
     * @return 结果
     */
    Result<PageData<CommentVo>> getChildCommentList(ChildCommentParam childCommentParam);

    /**
     * 获取评论表单
     * @param commentSearchParam 搜索参数
     * @return 结果
     */
    Result<PageData<CommentVo>> searchCommentList(CommentSearchParam commentSearchParam);
}
