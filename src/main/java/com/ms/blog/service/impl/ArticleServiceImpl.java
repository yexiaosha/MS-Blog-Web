package com.ms.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.common.ErrorCode;
import com.ms.blog.common.PageData;
import com.ms.blog.common.PageParam;
import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ServiceLog;
import com.ms.blog.dao.ArticleMapper;
import com.ms.blog.entity.Article;
import com.ms.blog.entity.param.ArticleParam;
import com.ms.blog.entity.param.ArticleSearchParam;
import com.ms.blog.entity.param.ArticleSimpleVo;
import com.ms.blog.entity.vo.ArticleVo;
import com.ms.blog.entity.vo.UserVo;
import com.ms.blog.service.ArticleService;
import com.ms.blog.service.CategoryService;
import com.ms.blog.service.TagService;
import com.ms.blog.service.UserService;
import com.ms.blog.util.ResultUtils;
import io.netty.util.internal.StringUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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

    @Resource
    private UserService userService;

    private static final String ARTICLE_ID_ = "ARTICLE_ID_";

    private static final String HOT_ARTICLE_LIST = "HOT_ARTICLE_LIST_";

    private static final String TAG_ID = "TAG_ID_";

    private static final String CATEGORY_ID = "CATEGORY_ID";

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
        articleVo = copy(article);
        redisTemplate.opsForValue().set(ARTICLE_ID_ + id, JSON.toJSONString(articleVo));
        return ResultUtils.success(articleVo);
    }

    @Override
    @ServiceLog("通过条件获取文章列表")
    public Result<PageData<ArticleVo>> getArticleListByType(ArticleSearchParam articleSearchParam) {
        Page<Article> articlePage = new Page<>(articleSearchParam.getCurrentPage(), articleSearchParam.getPageSize());
        IPage<Article> articleIPage = articleMapper.getArticleList(articlePage, articleSearchParam);
        List<Article> articleList = articleIPage.getRecords();
        List<ArticleVo> articleVoList = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(articleSearchParam.getArticleWriter())){
            UserVo data = userService.getUserInfo(articleSearchParam.getArticleWriter()).getData();
            List<Article> articles = articleList.stream().filter(aVo -> data.getId().equals(aVo.getId())).collect(Collectors.toList());
            for (Article article:articles) {
                articleVoList.add(copy(article));
            }
            PageData<ArticleVo> articleVoPageData = new PageData<>(articleVoList, articleIPage.getTotal(), articleIPage.getPages(), articleIPage.getCurrent());
            return ResultUtils.success(articleVoPageData);
        }

        for (Article article:articleList) {
            articleVoList.add(copy(article));
        }
        PageData<ArticleVo> articleVoPageData = new PageData<>(articleVoList, articleIPage.getTotal(), articleIPage.getPages(), articleIPage.getCurrent());
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
                .isPublish(2)
                .isSecret(articleParam.getIsSecret())
                .categoryId(categoryService.getCategoryByName(articleParam.getCategory()).getData().getId())
                .build();

        return setArticleTagRelate(articleParam, createTime, article);
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
                .isPublish(1)
                .isSecret(articleParam.getIsSecret())
                .categoryId(categoryService.getCategoryByName(articleParam.getCategory()).getData().getId())
                .build();

        return setArticleTagRelate(articleParam, createTime, article);
    }

    @Override
    @ServiceLog("更改文章")
    public Result<Integer> updateArticle(ArticleParam articleParam) {
        Article article = Article.builder()
                .id(articleParam.getId())
                .userId(articleParam.getUserId())
                .content(articleParam.getContent())
                .contentMd(articleParam.getContentMd())
                .avatar(articleParam.getAvatar())
                .isOriginal(articleParam.getIsOriginal())
                .remark(articleParam.getRemark())
                .summary(articleParam.getSummary())
                .updateDate(new Date())
                .title(articleParam.getTitle())
                .originalUrl(articleParam.getOriginalUrl())
                .isStick(articleParam.getIsStick())
                .isPublish(1)
                .isSecret(articleParam.getIsSecret())
                .categoryId(categoryService.getCategoryByName(articleParam.getCategory()).getData().getId())
                .build();
        articleMapper.updateArticleInfo(article);
        for (String a : articleParam.getTagList()) {
            Integer tagId = tagService.getTagByName(a).getData();
            if (tagId == null){
                log.info("标签不存在");
                continue;
            }
            articleMapper.insertArticleTagRelate(articleParam.getId() ,tagId);
        }
        return ResultUtils.success();
    }

    @Override
    @ServiceLog("删除文章")
    public Result<Integer> deleteArticle(Integer id) {
        Article article = new Article();
        article.setIsPublish(articleMapper.getArticleById(id).getIsPublish());
        if (article.getIsPublish() == null){
            return ResultUtils.fail(ErrorCode.ARTICLE_NOT_EXIST.getCode(), ErrorCode.ARTICLE_NOT_EXIST.getMsg());
        }
        article.setIsPublish(0);
        return ResultUtils.success(articleMapper.updateArticleInfo(article));
    }

    @Override
    @ServiceLog("获取标签中所有文章")
    @Transactional(rollbackFor = Exception.class)
    public Result<PageData<ArticleSimpleVo>> getArticleListByTag(Integer tagId, PageParam pageParam) {
        Page<Article> articlePage = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        List<Integer> articleIdList = articleMapper.getArticleIdByTagId(tagId);
        IPage<Article> articleIPage = articleMapper.getArticleByTag(articleIdList, articlePage);
        List<ArticleSimpleVo> articleSimpleVoList = new ArrayList<>();
        for (Article a : articleIPage.getRecords()) {
            ArticleSimpleVo articleSimpleVo = ArticleSimpleVo.builder()
                    .id(a.getId())
                    .title(a.getTitle())
                    .createDate(a.getCreateTime())
                    .nikeName(userService.getUserInfoDetailsByUserId(a.getUserId()).getNikeName())
                    .updateDate(a.getUpdateDate())
                    .summary(a.getSummary())
                    .username(userService.getUserByUserId(a.getUserId()).getUsername())
                    .build();
            articleSimpleVoList.add(articleSimpleVo);
        }

        Integer clickVol = JSON.parseObject(redisTemplate.opsForValue().get(TAG_ID + tagId), Integer.class);
        if (clickVol != null){
            redisTemplate.opsForValue().set(TAG_ID + tagId, JSON.toJSONString(clickVol + 1));
        }
        clickVol = tagService.getTagById(tagId).getClickVolume();
        redisTemplate.opsForValue().set(TAG_ID + tagId, JSON.toJSONString(clickVol + 1));

        PageData<ArticleSimpleVo> articleSimpleVoPageData = new PageData<>(articleSimpleVoList, articleIPage.getTotal(), articleIPage.getPages(), articleIPage.getCurrent());
        return ResultUtils.success(articleSimpleVoPageData);
    }

    @Override
    @ServiceLog("获取分类的所有文章")
    public Result<PageData<ArticleSimpleVo>> getArticleListByCategory(Integer categoryId, PageParam pageParam) {
        Page<Article> articlePage = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        IPage<Article> articleIPage = articleMapper.getArticleByCategory(categoryId, articlePage);
        List<ArticleSimpleVo> articleSimpleVoList = new ArrayList<>();
        for (Article a : articleIPage.getRecords()) {
            ArticleSimpleVo articleSimpleVo = ArticleSimpleVo.builder()
                    .id(a.getId())
                    .title(a.getTitle())
                    .createDate(a.getCreateTime())
                    .nikeName(userService.getUserInfoDetailsByUserId(a.getUserId()).getNikeName())
                    .updateDate(a.getUpdateDate())
                    .summary(a.getSummary())
                    .username(userService.getUserByUserId(a.getUserId()).getUsername())
                    .build();
            articleSimpleVoList.add(articleSimpleVo);
        }

        Integer clickVol = JSON.parseObject(redisTemplate.opsForValue().get(CATEGORY_ID + categoryId), Integer.class);
        if (clickVol != null){
            redisTemplate.opsForValue().set(CATEGORY_ID + categoryId, JSON.toJSONString(clickVol + 1));
        }
        clickVol = categoryService.getCategoryById(categoryId).getData().getClickVolume();
        redisTemplate.opsForValue().set(CATEGORY_ID + categoryId, JSON.toJSONString(clickVol + 1));

        PageData<ArticleSimpleVo> articleSimpleVoPageData = new PageData<>(articleSimpleVoList, articleIPage.getTotal(), articleIPage.getPages(), articleIPage.getCurrent());
        return ResultUtils.success(articleSimpleVoPageData);
    }

    private Result<Integer> setArticleTagRelate(ArticleParam articleParam, Date createTime, Article article) {
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
            Integer tagId = tagService.getTagByName(name).getData();
            if (tagId == null){
                log.info("标签不存在");
                continue;
            }
            articleMapper.insertArticleTagRelate(articleId, tagId);
        }

        return ResultUtils.success("暂存成功");
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
