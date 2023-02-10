package com.ms.blog.service;

/**
 * 定时任务类
 * @author wyh
 * @date 2023/01/16 15:59
 */
public interface CronJobService {

    /**
     * 实时更新阅读量
     */
    void updateArticleQuantity();

    /**
     * 定时更新热门文章
     */
    void updatePopularArticle();

    /**
     * 定时更新热门标签
     */
    void updatePopularTag();

    /**
     * 定时更新标签点击量
     */
    void updateTagClickVolume();

    /**
     * 定时更新热门分类
     */
    void updatePopularCategory();

    /**
     * 定时更新分类点击量
     */
    void updateCategoryClickVolume();
}
