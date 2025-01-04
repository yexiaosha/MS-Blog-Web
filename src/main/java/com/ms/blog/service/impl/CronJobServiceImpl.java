package com.ms.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.ms.blog.dao.ArticleMapper;
import com.ms.blog.dao.CategoryMapper;
import com.ms.blog.dao.TagMapper;
import com.ms.blog.entity.Article;
import com.ms.blog.entity.Tag;
import com.ms.blog.entity.vo.ArticleVo;
import com.ms.blog.entity.vo.CategoryVo;
import com.ms.blog.entity.vo.TagVo;
import com.ms.blog.service.CategoryService;
import com.ms.blog.service.CronJobService;
import com.ms.blog.service.TagService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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

    @Resource
    private CategoryMapper categoryMapper;

    private static final String ARTICLE_ID_ = "ARTICLE_ID_";

    private static final String HOT_ARTICLE_LIST = "HOT_ARTICLE_LIST_";

    private static final String HOT_TAG_LIST = "HOT_TAG_LIST_";

    private static final String TAG_ID = "TAG_ID_";

    public static final String HOT_CATEGORY_LIST = "HOT_CATEGORY_LIST_";

    private static final String CATEGORY_ID = "CATEGORY_ID";

    private static final String ARTICLE_LIKE_COUNT = "ARTICLE_LIKE_COUNT_";

    @Value("${blog.mark-days}")
    private Integer markDays;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private TagMapper tagMapper;

    @Override
    @Scheduled(cron = "0/5 * * * * ?")
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
    @Scheduled(cron = "0 0 6 * * ?")
    @Transactional(rollbackFor = Exception.class)
    @PostConstruct
    public void updatePopularArticle() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -markDays);
        Date date = calendar.getTime();
        List<Article> articleList = articleMapper.getPopularArticleList(date);
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
    @Scheduled(cron = "0 0 6 * * ?")
    @Transactional(rollbackFor = Exception.class)
    @PostConstruct
    public void updatePopularTag() {
        List<Tag> tagList = tagMapper.getPopularTagList();
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag t : tagList) {
            TagVo tagVo = new TagVo();
            BeanUtils.copyProperties(t, tagVo);
            tagVoList.add(tagVo);
        }
        List<TagVo> tagVoStream = tagVoList.stream()
                .sorted(Comparator.comparingInt(tVo -> -(tVo.getClickVolume())))
                .limit(5)
                .collect(Collectors.toList());
        redisTemplate.opsForValue().set(HOT_TAG_LIST, JSON.toJSONString(tagVoStream));
    }

    @Override
    @Scheduled(cron = "0/5 * * * * ?")
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

    @Override
    @Scheduled(cron = "0 0 6 * * ?")
    @Transactional(rollbackFor = Exception.class)
    @PostConstruct
    public void updatePopularCategory() {
        List<CategoryVo> categoryList = categoryService.getCategoryList().getData();
        List<CategoryVo>categoryVoListStream = categoryList.stream()
                .sorted(Comparator.comparingInt(cVo -> -(cVo.getClickVolume())))
                .limit(5)
                .collect(Collectors.toList());
        redisTemplate.opsForValue().set(HOT_CATEGORY_LIST, JSON.toJSONString(categoryVoListStream));
    }

    @Override
    @Scheduled(cron = "0/5 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void updateCategoryClickVolume() {
        Set<String> keys = redisTemplate.keys(CATEGORY_ID.concat("*"));
        if (keys != null){
            for (String key : keys) {
                Integer volume = JSON.parseObject(redisTemplate.opsForValue().get(CATEGORY_ID + key), Integer.class);
                if (volume != null){
                    categoryMapper.updateCategoryClickVolume(Integer.valueOf(key), volume);
                }
            }
        }
    }

    @Override
    @Scheduled(cron = "0/2 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void updateArticleLikeCount() {
        Set<String> keys = redisTemplate.keys(ARTICLE_LIKE_COUNT.concat("*"));
        if (keys != null){
            for (String k : keys) {
                Integer likeCount = JSON.parseObject(redisTemplate.opsForValue().get(k), Integer.class);
                if (likeCount != null){
                    articleMapper.updateArticleLikeCount(Integer.valueOf(StrUtil.removePrefix(k, ARTICLE_LIKE_COUNT)), likeCount);
                }
            }
        }
    }

    private ArticleVo copy(Article article){
        ArticleVo articleVo = ArticleVo.builder()
                .build();
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setCategoryVo(categoryService.getCategoryById(article.getCategoryId()).getData());
        articleVo.setTagsVo(tagService.getTagListByArticleId(articleVo.getId()).getData());
        return articleVo;
    }
}
