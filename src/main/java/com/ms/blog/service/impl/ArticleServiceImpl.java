package com.ms.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.common.ErrorCode;
import com.ms.blog.common.PageData;
import com.ms.blog.common.PageParam;
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
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
    @ServiceLog("获取所有文章")
    public Result<PageData<ArticleVo>> getArticleList(PageParam pageParam) {
        Page<ArticleVo> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        IPage<ArticleVo> iPage = articleMapper.getArticleList(page);
        PageData<ArticleVo> pageData = new PageData<>(iPage, pageParam.getCurrentPage().longValue());
        return ResultUtils.success(pageData);
    }

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
           redisTemplate.opsForValue().set(ARTICLE_ID_ + id, JSON.toJSONString(articleVo));
           return ResultUtils.success(articleVo);
        }

        articleVo = articleMapper.getArticleContent(id);

        if (articleVo == null){
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        articleVo.setQuantity(articleVo.getQuantity() + 1);
        redisTemplate.opsForValue().set(ARTICLE_ID_ + articleVo.getId(), JSON.toJSONString(articleVo));
        return ResultUtils.success(articleVo);
    }

    @Override
    @ServiceLog("通过条件获取文章列表")
    public Result<PageData<ArticleVo>> getArticleListByType(ArticleConditionParam articleConditionParam) {
        Page<ArticleVo> articleVoPage = new Page<>(articleConditionParam.getCurrentPage(), articleConditionParam.getPageSize());
        IPage<ArticleVo> articleVoIPage = articleMapper.getArticleList(articleVoPage);
        PageData<ArticleVo> articleVoPageData = new PageData<>(articleVoIPage, articleConditionParam.getCurrentPage().longValue());
        return ResultUtils.success(articleVoPageData);
    }


    @Override
    @ServiceLog("新增文章")
    public Result<Integer> insertArticle(ArticleParam articleParam, Integer userId) {
        Article article = Article.builder()
                .userId(userId)
                .content(articleParam.getContent())
                .contentMd(articleParam.getContentMd())
                .createTime(new Date())
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

        return ResultUtils.success(articleMapper.insertArticle(article));
    }

    @Override
    @ServiceLog("暂存文章")
    public Result<ArticleVo> temporaryArticle(ArticleParam articleParam) {
        return null;
    }

    @Override
    @ServiceLog("更改文章")
    public Result<ArticleVo> updateArticle(Integer id) {
        return null;
    }

    @Override
    @ServiceLog("获取文章标签")
    public Result<List<Tag>> getArticleTags(Integer id) {
        return null;
    }
}
