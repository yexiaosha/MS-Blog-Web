package com.ms.blog.service.impl;

import com.ms.blog.common.aspect.annotation.ServiceLog;
import com.ms.blog.dao.ArticleMapper;
import com.ms.blog.entity.vo.ArticleVo;
import com.ms.blog.service.CronJobService;
import java.util.Set;
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
    private RedisTemplate<String, ArticleVo> redisTemplate;

    public static final String ARTICLE_ID_ = "ARTICLE_ID_";

    @Resource
    private ArticleMapper articleMapper;

    @Override
    @ServiceLog("阅读量定时任务")
    @Scheduled(cron = "0 */1 * * * ?")
    public void updateArticleQuantity() {
        Set<String> keys = redisTemplate.keys(ARTICLE_ID_.concat("*"));
        if (keys != null){
            for (String key : keys) {
                ArticleVo articleVo = redisTemplate.opsForValue().get(key);
                articleMapper.updateArticleQuantity(articleVo.getId(), articleVo.getQuantity());
            }
        }
    }
}
