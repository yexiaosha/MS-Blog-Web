package com.ms.blog.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.entity.Article;
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
    IPage<Article> getArticleList(Page<Article> page);

    /**
     * 获取热门文章
     * @return 文章列表
     */
    List<Article> getPopularArticleList();

    Article getArticleContent(Integer id);

    int updateArticleQuantity(Integer id, Integer quantity);

    int insertArticle(Article article);

    int insertArticleTagRelate(Integer articleId, Integer tagId);

    List<Article> getArticleByUserId(Integer userId);

    int updateArticleInfo(Article article);
}