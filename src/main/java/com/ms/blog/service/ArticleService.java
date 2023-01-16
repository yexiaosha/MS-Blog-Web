package com.ms.blog.service;

import com.ms.blog.common.PageData;
import com.ms.blog.common.PageParam;
import com.ms.blog.common.Result;
import com.ms.blog.entity.Tag;
import com.ms.blog.entity.param.ArticleConditionParam;
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
     * 获取所有文章列表
     * @return 分页结果
     */
    Result<PageData<ArticleVo>> getArticleList(PageParam pageParam);

    /**
     * 获取热门文章
     * @return 分页结果
     */
    Result<PageData<ArticleVo>> getPopularArticleList(PageParam pageParam);

    /**
     * 查看文章详情
     * @param id  文章id
     * @return  文章结果
     */
    Result<ArticleVo> getArticleContent(Integer id);

    /**
     * 根据条件搜索文章
     * @param articleConditionParam 搜索参数
     * @return  搜索结果
     */
    Result<PageData<ArticleVo>> getArticleListByType(ArticleConditionParam articleConditionParam);

    /**
     * 删除文章
     * @param articleIdList 文章列表
     * @return  删除结果
     */
    Result<Integer> deleteArticles(List<Integer> articleIdList);

    /**
     * 自增文章
     * @param articleParam 文章参数
     * @return  结果
     */
    Result<Integer> insertArticle(ArticleParam articleParam);

    /**
     * 暂存文章
     * @param articleParam 文章参数
     * @return  文章现状
     */
    Result<ArticleVo> temporaryArticle(ArticleParam articleParam);

    /**
     * 更新文章
     * @param id 文章id
     * @return  文章修改后结果
     */
    Result<ArticleVo> updateArticle(Integer id);

    /**
     * 获取文章tag
     * @param id 文章id
     * @return 文章标签列表
     */
    Result<List<Tag>> getArticleTags(Integer id);
}
