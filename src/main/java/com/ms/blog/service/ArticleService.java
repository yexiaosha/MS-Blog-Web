package com.ms.blog.service;

import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.entity.param.ArticleSearchParam;
import com.ms.blog.entity.param.ArticleParam;
import com.ms.blog.entity.vo.ArticleVo;
import java.util.List;

/**
 * 文章业务类接口
 * @author wyh
 * @date 2023/01/16 11:03
 */
public interface ArticleService {

    /**
     * 获取热门文章
     * @return 分页结果
     */
    Result<List<ArticleVo>> getPopularArticleList();

    /**
     * 查看文章详情
     * @param id  文章id
     * @return  文章结果
     */
    Result<ArticleVo> getArticleContent(Integer id);

    /**
     * 根据条件搜索文章
     * @param articleSearchParam 搜索参数
     * @return  搜索结果
     */
    Result<PageData<ArticleVo>> getArticleListByType(ArticleSearchParam articleSearchParam);

    /**
     * 增加文章
     * @param articleParam 文章参数
     * @param userId 用户id
     * @return  结果
     */
    Result<Integer> insertArticle(ArticleParam articleParam, Integer userId);

    /**
     * 暂存文章
     * @param articleParam 文章参数
     * @param userId 用户id
     * @return  文章现状
     */
    Result<Integer> temporaryArticle(ArticleParam articleParam, Integer userId);

    /**
     * 更新文章
     * @param articleParam 文章参数
     * @return  文章修改后结果
     */
    Result<Integer> updateArticle(ArticleParam articleParam);

    /**
     * 删除文章
     * @param id 文章id
     * @return 通用结果
     */
    Result<Integer> deleteArticle(Integer id);

}
