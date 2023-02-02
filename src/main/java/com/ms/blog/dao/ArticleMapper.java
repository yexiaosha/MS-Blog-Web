package com.ms.blog.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.entity.Article;
import com.ms.blog.entity.param.ArticleConditionParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章接口
 * @author wyh
 * @date 2023/01/16 14:36
 */
@Mapper
public interface ArticleMapper {

    /**
     * 通过条件获取文章列表
     * @param page 分页参数
     * @return 分页列表
     */
    IPage<Article> getArticleList(Page<Article> page, ArticleConditionParam articleConditionParam);

    /**
     * 获取热门文章
     * @return 文章列表
     */
    List<Article> getPopularArticleList();

    /**
     * 获取文章详情
     * @param id 文章id
     * @return 文章对象
     */
    Article getArticleContent(Integer id);

    /**
     * 更新文章点击量
     * @param id 文章id
     * @param quantity 文章和点击量
     * @return 是否成功
     */
    int updateArticleQuantity(Integer id, Integer quantity);

    /**
     *  新增文章
     * @param article 文章类
     * @return 结果
     */
    int insertArticle(Article article);

    /**
     * 新增文章标签关联表
     * @param articleId 文章id
     * @param tagId 标签id
     * @return 结果
     */
    int insertArticleTagRelate(Integer articleId, Integer tagId);

    /**
     * 根据用户id获取文章列表
     * @param userId 用户id
     * @return 文章列表
     */
    List<Article> getArticleByUserId(Integer userId);

    /**
     * 更改文章信息
     * @param article 文章类
     * @return 结果
     */
    int updateArticleInfo(Article article);

    /**
     * 通过文章id获取文章
     * @param id 文章id
     * @return 文章类
     */
    Article getArticleById(Integer id);
}
