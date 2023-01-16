package com.ms.blog.service.impl;

import com.ms.blog.common.aspect.annotation.ServiceLog;
import com.ms.blog.service.CronJobService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 定时任务事务接口实现
 * @author wyh
 * @date 2023/01/16 16:01
 */
@Service
@Slf4j
public class CronJobServiceImpl implements CronJobService {

    @Resource
    private RedisTemplate<Integer, Integer> redisTemplate;

    @Override
    @ServiceLog("阅读量定时任务")
    @Scheduled(cron = "0 */1 * * * ?")
    public void updateArticleQuantity() {

    }
}
