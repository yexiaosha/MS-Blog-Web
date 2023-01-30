package com.ms.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.ms.blog.common.aspect.annotation.ServiceLog;
import com.ms.blog.dao.ArticleMapper;
import com.ms.blog.entity.vo.ArticleVo;
import com.ms.blog.service.CronJobService;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
    private RedisTemplate<String, String> redisTemplate;

    private static final String ARTICLE_ID_ = "ARTICLE_ID_";

    private static final String HOT_ARTICLE_LIST = "HOT_ARTICLE_LIST_";

    @Resource
    private ArticleMapper articleMapper;

    @Override
    @ServiceLog("阅读量定时任务")
    @Scheduled(cron = "0 */1 * * * ?")
    public void updateArticleQuantity() {
        Set<String> keys = redisTemplate.keys(ARTICLE_ID_.concat("*"));
        if (keys != null){
            for (String key : keys) {
                ArticleVo articleVo = JSON.parseObject(redisTemplate.opsForValue().get(key), ArticleVo.class);
                if (articleVo != null) {
                    articleMapper.updateArticleQuantity(articleVo.getId(), articleVo.getQuantity());
                }
            }
        }
    }

    @Override
    @ServiceLog("热门文章定时任务")
    @Scheduled(cron = "0 0 6 * * ?")
    public void updatePopularArticle() {
        List<ArticleVo> articleVoList = articleMapper.getPopularArticleList();
        List<ArticleVo> articleVoStream = articleVoList.stream()
                .sorted(Comparator.comparingInt(articleVo -> -(articleVo.getQuantity())))
                .limit(5)
                .collect(Collectors.toList());
        redisTemplate.opsForValue().set(HOT_ARTICLE_LIST, JSON.toJSONString(articleVoStream));
    }

}
