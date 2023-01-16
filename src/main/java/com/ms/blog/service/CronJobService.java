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
}
