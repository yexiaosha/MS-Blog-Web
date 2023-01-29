package com.ms.blog.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.entity.vo.ArticleVo;
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
    IPage<ArticleVo> getArticleList(Page<ArticleVo> page);

    IPage<ArticleVo> getPopularArticleList(Page<ArticleVo> page);

    ArticleVo getArticleContent(Integer id);

    int updateArticleQuantity(Integer id, Integer quantity);
}
