package com.ms.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.common.ErrorCode;
import com.ms.blog.common.PageData;
import com.ms.blog.common.PageParam;
import com.ms.blog.common.Result;
import com.ms.blog.common.aspect.annotation.ServiceLog;
import com.ms.blog.dao.ArticleMapper;
import com.ms.blog.entity.Tag;
import com.ms.blog.entity.param.ArticleConditionParam;
import com.ms.blog.entity.param.ArticleParam;
import com.ms.blog.entity.vo.ArticleVo;
import com.ms.blog.service.ArticleService;
import com.ms.blog.util.ResultUtils;
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
    private RedisTemplate<Integer, ArticleVo> redisTemplate;

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
    public Result<PageData<ArticleVo>> getPopularArticleList(PageParam pageParam) {
        Page<ArticleVo> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        IPage<ArticleVo> iPage = articleMapper.getPopularArticleList(page);
        PageData<ArticleVo> pageData = new PageData<>(iPage, pageParam.getCurrentPage().longValue());
        return ResultUtils.success(pageData);
    }

    @Override
    @ServiceLog("获取文章内容")
    public Result<ArticleVo> getArticleContent(Integer id) {
        if (redisTemplate.opsForValue().get(id) != null){
           ArticleVo articleVo = redisTemplate.opsForValue().get(id);
           articleVo.setQuantity(articleVo.getQuantity() + 1);
           redisTemplate.opsForValue().set(id, articleVo);
           return ResultUtils.success(articleVo);
        }

        ArticleVo articleVo = articleMapper.getArticleContent(id);

        if (articleVo == null){
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        articleVo.setQuantity(articleVo.getQuantity() + 1);
        redisTemplate.opsForValue().set(articleVo.getId(), articleVo);
        return ResultUtils.success(articleVo);
    }

    @Override
    @ServiceLog("通过条件获取文章列表")
    public Result<PageData<ArticleVo>> getArticleListByType(ArticleConditionParam articleConditionParam) {
        return null;
    }

    @Override
    public Result<Integer> deleteArticles(List<Integer> articleIdList) {
        return null;
    }

    @Override
    public Result<Integer> insertArticle(ArticleParam articleParam) {
        return null;
    }

    @Override
    public Result<ArticleVo> temporaryArticle(ArticleParam articleParam) {
        return null;
    }

    @Override
    public Result<ArticleVo> updateArticle(Integer id) {
        return null;
    }

    @Override
    public Result<List<Tag>> getArticleTags(Integer id) {
        return null;
    }
}
