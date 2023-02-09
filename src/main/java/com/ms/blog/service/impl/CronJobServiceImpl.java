package com.ms.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.ms.blog.common.annotation.ServiceLog;
import com.ms.blog.dao.ArticleMapper;
import com.ms.blog.dao.TagMapper;
import com.ms.blog.entity.Article;
import com.ms.blog.entity.Tag;
import com.ms.blog.entity.vo.ArticleVo;
import com.ms.blog.entity.vo.TagVo;
import com.ms.blog.service.CategoryService;
import com.ms.blog.service.CronJobService;
import com.ms.blog.service.TagService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private CategoryService categoryService;

    @Resource
    private TagService tagService;

    private static final String ARTICLE_ID_ = "ARTICLE_ID_";

    private static final String HOT_ARTICLE_LIST = "HOT_ARTICLE_LIST_";

    private static final String HOT_TAG_LIST = "HOT_TAG_LIST_";

    private static final String TAG_ID = "TAG_ID_";

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private TagMapper tagMapper;

    @Override
    @ServiceLog("阅读量定时任务")
    @Scheduled(cron = "0 */1 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void updateArticleQuantity() {
        Set<String> keys = redisTemplate.keys(ARTICLE_ID_.concat("*"));
        if (keys != null){
            for (String key : keys) {
                ArticleVo articleVo = JSON.parseObject(redisTemplate.opsForValue().get(ARTICLE_ID_ + key), ArticleVo.class);
                if (articleVo != null) {
                    articleMapper.updateArticleQuantity(articleVo.getId(), articleVo.getQuantity());
                }
            }
        }
    }

    @Override
    @ServiceLog("热门文章定时任务")
    @Scheduled(cron = "0 0 6 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void updatePopularArticle() {
        List<Article> articleList = articleMapper.getPopularArticleList();
        List<ArticleVo>articleVoList = new ArrayList<>();
        for (Article article : articleList) {
            articleVoList.add(copy(article));
        }
        List<ArticleVo> articleVoStream = articleVoList.stream()
                .sorted(Comparator.comparingInt(articleVo -> -(articleVo.getQuantity())))
                .limit(5)
                .collect(Collectors.toList());
        redisTemplate.opsForValue().set(HOT_ARTICLE_LIST, JSON.toJSONString(articleVoStream));
    }

    @Override
    @ServiceLog("热门标签定时任务")
    @Scheduled(cron = "0 0 6 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void updatePopularTag() {
        List<Tag> tagList = tagMapper.getPopularTagList();
        List<TagVo> tagVoList = new ArrayList<>();
        TagVo tagVo = new TagVo();
        for (Tag t : tagList) {
            BeanUtils.copyProperties(t, tagVo);
            tagVoList.add(tagVo);
        }
        List<TagVo> tagVoStream = tagVoList.stream()
                .sorted(Comparator.comparingInt(tVo -> -(tagVo.getClickVolume())))
                .limit(5)
                .collect(Collectors.toList());
        redisTemplate.opsForValue().set(HOT_TAG_LIST, JSON.toJSONString(tagVoStream));
    }

    @Override
    @ServiceLog("定时更新标签点击量")
    @Scheduled(cron = "0 */1 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void updateTagClickVolume() {
        Set<String> keys = redisTemplate.keys(TAG_ID.concat("*"));
        if (keys != null){
            for (String key : keys) {
                Integer volume = JSON.parseObject(redisTemplate.opsForValue().get(TAG_ID + key), Integer.class);
                if (volume != null){
                    tagMapper.updateTagClickVolume(Integer.valueOf(key), volume);
                }
            }
        }
    }

    private ArticleVo copy(Article article){
        ArticleVo articleVo = ArticleVo.builder()
                .build();
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setCategoryVo(categoryService.getCategoryById(articleVo.getId()).getData());
        articleVo.setTagsVo(tagService.getTagListByArticleId(articleVo.getId()).getData());
        return articleVo;
    }
}
