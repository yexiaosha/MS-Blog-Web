package com.ms.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.entity.Article;
import com.ms.blog.entity.param.ArticleSearchParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 文章接口
 * @author wyh
 * @date 2023/01/16 14:36
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 通过条件获取文章列表
     * @param page 分页参数
     * @return 分页列表
     */
    IPage<Article> getArticleList(Page<Article> page, ArticleSearchParam articleSearchParam);

    /**
     * 获取热门文章
     * @return 文章列表
     */
    List<Article> getPopularArticleList(Date date);

    /**
     * 获取文章详情
     * @param id 文章id
     * @return 文章对象
     */
    Article getArticleContent(@Param("id") Integer id);

    /**
     * 更新文章点击量
     * @param id 文章id
     * @param quantity 文章和点击量
     * @return 是否成功
     */
    int updateArticleQuantity(@Param("id") Integer id, @Param("quantity") Integer quantity);

    /**
     *  新增文章
     * @param article 文章类
     * @return 结果
     */
    int insertArticle(@Param("article") Article article);

    /**
     * 新增文章标签关联表
     * @param articleId 文章id
     * @param tagId 标签id
     * @return 结果
     */
    int insertArticleTagRelate(@Param("articleId") Integer articleId, @Param("tagId") Integer tagId);

    /**
     * 根据用户id获取文章列表
     * @param userId 用户id
     * @return 文章列表
     */
    List<Article> getArticleByUserId(@Param("userId") Integer userId);

    /**
     * 更改文章信息
     * @param article 文章类
     * @return 结果
     */
    int updateArticleInfo(@Param("article") Article article);

    /**
     * 通过文章id获取文章
     * @param id 文章id
     * @return 文章类
     */
    Article getArticleById(@Param("id") Integer id);

    /**
     * 通过文章id列表获取文章
     * @param articleIdList 文章id列表
     * @param articlePage 文章分页参数
     * @return 分页结果
     */
    IPage<Article> getArticleByTag(List<Integer> articleIdList, Page<Article> articlePage);

    /**
     * 根据标签id获取文章id列表
     * @param tagId 标签id
     * @return 文章id列表
     */
    List<Integer> getArticleIdByTagId(@Param("tagId") Integer tagId);

    /**
     * 通过分类获取文章
     * @param categoryId    分类id
     * @param articlePage   文章分页参数
     * @return 分页结果
     */
    IPage<Article> getArticleByCategory(@Param("categoryId") Integer categoryId, Page<Article> articlePage);

    /**
     * 更新文章点赞数
     * @param id 文章id
     * @return 结果
     */
    int updateArticleLikeCount(@Param("id") Integer id, @Param("likeCount") Integer likeCount);

    /**
     * 获取最新文章
     * @param date 日期
     * @return 文章列表
     */
    List<Article> getNewestArticleList(Date date);
}
