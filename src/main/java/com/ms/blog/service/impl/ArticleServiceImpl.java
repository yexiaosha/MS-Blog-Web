package com.ms.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.common.ErrorCode;
import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.common.aspect.annotation.ServiceLog;
import com.ms.blog.dao.ArticleMapper;
import com.ms.blog.entity.Article;
import com.ms.blog.entity.Tag;
import com.ms.blog.entity.param.ArticleConditionParam;
import com.ms.blog.entity.param.ArticleParam;
import com.ms.blog.entity.vo.ArticleVo;
import com.ms.blog.service.ArticleService;
import com.ms.blog.service.CategoryService;
import com.ms.blog.service.TagService;
import com.ms.blog.util.ResultUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文章业务类实现
 * @author wyh
 * @date 2023/01/16 11:03
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private CategoryService categoryService;

    @Resource
    private TagService tagService;

    private static final String ARTICLE_ID_ = "ARTICLE_ID_";

    private static final String HOT_ARTICLE_LIST = "HOT_ARTICLE_LIST_";

    @Override
    @ServiceLog("获取热门文章")
    public Result<List<ArticleVo>> getPopularArticleList() {
        return ResultUtils.success(JSON.parseArray(redisTemplate.opsForValue().get(HOT_ARTICLE_LIST), ArticleVo.class));
    }

    @Override
    @ServiceLog("获取文章内容")
    public Result<ArticleVo> getArticleContent(Integer id) {
        ArticleVo articleVo = JSON.parseObject(redisTemplate.opsForValue().get(id), ArticleVo.class);
        if (articleVo != null){
           articleVo.setQuantity(articleVo.getQuantity() + 1);
           redisTemplate.delete(ARTICLE_ID_ + id);
           redisTemplate.opsForValue().set(ARTICLE_ID_ + id, JSON.toJSONString(articleVo));
           return ResultUtils.success(articleVo);
        }

        Article article = articleMapper.getArticleContent(id);
        return ResultUtils.success(copy(article));
    }

    @Override
    @ServiceLog("通过条件获取文章列表")
    public Result<PageData<ArticleVo>> getArticleListByType(ArticleConditionParam articleConditionParam) {
        Page<Article> articlePage = new Page<>(articleConditionParam.getCurrentPage(), articleConditionParam.getPageSize());
        IPage<Article> articleIPage = articleMapper.getArticleList(articlePage);
        PageData<Article> articlePageData = new PageData<>(articleIPage, articleConditionParam.getCurrentPage().longValue());
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article article:articlePageData.getPageData()) {
            articleVoList.add(copy(article));
        }
        PageData<ArticleVo> articleVoPageData = new PageData<>(articleVoList, articlePageData.getTotal(),
                articlePageData.getTotalPages(), articleConditionParam.getCurrentPage().longValue());
        return ResultUtils.success(articleVoPageData);
    }


    @Override
    @ServiceLog("新增文章")
    @Transactional(rollbackFor = Exception.class)
    public Result<Integer> insertArticle(ArticleParam articleParam, Integer userId) {
        Date createTime = new Date();
        Article article = Article.builder()
                .userId(userId)
                .content(articleParam.getContent())
                .contentMd(articleParam.getContentMd())
                .createTime(createTime)
                .avatar(articleParam.getAvatar())
                .isOriginal(articleParam.getIsOriginal())
                .remark(articleParam.getRemark())
                .summary(articleParam.getSummary())
                .updateDate(new Date())
                .title(articleParam.getTitle())
                .originalUrl(articleParam.getOriginalUrl())
                .isStick(0)
                .isPublish(1)
                .isSecret(articleParam.getIsSecret())
                .categoryId(categoryService.getCategoryByName(articleParam.getCategory()).getData().getId())
                .build();

        setArticleTagRelate(articleParam, createTime, article);

        return ResultUtils.success("发布成功");
    }

    @Override
    @ServiceLog("暂存文章")
    @Transactional(rollbackFor = Exception.class)
    public Result<Integer> temporaryArticle(ArticleParam articleParam, Integer userId) {
        Date createTime = new Date();
        Article article = Article.builder()
                .userId(userId)
                .content(articleParam.getContent())
                .contentMd(articleParam.getContentMd())
                .createTime(createTime)
                .avatar(articleParam.getAvatar())
                .isOriginal(articleParam.getIsOriginal())
                .remark(articleParam.getRemark())
                .summary(articleParam.getSummary())
                .updateDate(new Date())
                .title(articleParam.getTitle())
                .originalUrl(articleParam.getOriginalUrl())
                .isStick(0)
                .isPublish(0)
                .isSecret(articleParam.getIsSecret())
                .categoryId(categoryService.getCategoryByName(articleParam.getCategory()).getData().getId())
                .build();

        setArticleTagRelate(articleParam, createTime, article);
        return ResultUtils.success("暂存成功");
    }

    @Override
    @ServiceLog("更改文章")
    public Result<Integer> updateArticle(ArticleParam articleParam) {

        return null;
    }

    @Override
    @ServiceLog("删除文章")
    public Result<Integer> deleteArticle(Integer id) {
        return null;
    }


    @Override
    @ServiceLog("获取文章标签")
    public Result<List<Tag>> getArticleTags(Integer id) {
        return null;
    }

    private void setArticleTagRelate(ArticleParam articleParam, Date createTime, Article article) {
        articleMapper.insertArticle(article);
        List<Article> articleList = articleMapper.getArticleByUserId(articleParam.getUserId());
        Integer articleId = null;
        for (Article a : articleList) {
            if (Objects.equals(a.getTitle(), articleParam.getTitle()) && a.getCreateTime() == createTime){
                articleId = a.getId();
                break;
            }
        }

        if (articleId == null){
            ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        for (String name : articleParam.getTagList()) {
            Integer tagId = tagService.getTagIdByName(name).getData().getId();
            articleMapper.insertArticleTagRelate(articleId, tagId);
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