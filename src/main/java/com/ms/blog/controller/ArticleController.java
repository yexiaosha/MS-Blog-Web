package com.ms.blog.controller;

import com.ms.blog.common.PageData;
import com.ms.blog.common.PageParam;
import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ControllerLog;
import com.ms.blog.entity.param.ArticleParam;
import com.ms.blog.entity.param.ArticleSearchParam;
import com.ms.blog.entity.vo.ArticleSimpleVo;
import com.ms.blog.entity.vo.ArticleVo;
import com.ms.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章接口
 * @author wyh
 * @date 2023/01/05 17:57
 */

@RestController
@Api(tags = "文章接口")
@RequestMapping("/msblog/article")
@Validated
@CrossOrigin
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping("/list")
    @ApiOperation("根据某一条件获取相应文章列表")
    @ControllerLog("根据某一条件获取相应文章列表")
    public Result<PageData<ArticleSimpleVo>> getArticleListByType(@RequestBody ArticleSearchParam articleSearchParam){
        return articleService.getArticleListByType(articleSearchParam);
    }

    @GetMapping("/list/hot")
    @ApiOperation("获取热门文章")
    @ControllerLog("获取热门文章")
    public Result<List<ArticleVo>> getPopularArticleList(){
        return articleService.getPopularArticleList();
    }

    @GetMapping("/content")
    @ApiOperation("获取文章详情")
    @ControllerLog("获取文章详情")
    @ApiImplicitParams(
            @ApiImplicitParam(value = "文章id", name = "id")
    )
    public Result<ArticleVo> getArticleContent(@RequestParam Integer id){
        return articleService.getArticleContent(id);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除文章")
    @ControllerLog("删除文章")
    @ApiImplicitParams(
            @ApiImplicitParam(value = "文章id", name = "articleId")
    )
    public Result<Integer> deleteArticles(@PathVariable("id") Integer articleId){
        return articleService.deleteArticle(articleId);
    }

    @PostMapping("/insert")
    @ApiOperation("新增文章")
    @ControllerLog("新增文章")
    public Result<Integer> insertArticle(@RequestBody ArticleParam articleParam){
        //User user = UserThreadLocal.get();
        return articleService.insertArticle(articleParam, articleParam.getUserId());
    }

    @PostMapping("/temporary")
    @ApiOperation("暂存文章")
    @ControllerLog("暂存文章")
    public Result<Integer> temporaryArticle(@RequestBody ArticleParam articleParam){
        //User user = UserThreadLocal.get();
        return articleService.temporaryArticle(articleParam, articleParam.getUserId());
    }

    @PostMapping("/update")
    @ApiOperation("更改文章")
    @ControllerLog("更改文章")
    public Result<Integer> updateArticle(@RequestBody ArticleParam articleParam){
        return articleService.updateArticle(articleParam);

    }

    @GetMapping("/tag")
    @ApiOperation("获取该标签的文章列表")
    @ControllerLog("获取该标签的文章列表")
    public Result<PageData<ArticleSimpleVo>> getArticleListByTag(@RequestParam Integer tagId, @RequestParam Integer currentPage, @RequestParam Integer pageSize){
        PageParam pageParam = new PageParam();
        pageParam.setCurrentPage(currentPage);
        pageParam.setPageSize(pageSize);
        return articleService.getArticleListByTag(tagId, pageParam);
    }

    @GetMapping("/category")
    @ApiOperation("获取该分类的文章列表")
    @ControllerLog("获取该分类的文章列表")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "categoryId", value = "分类id")
    )
    public Result<PageData<ArticleSimpleVo>> getArticleListByCategory(@RequestParam Integer categoryId, @RequestParam Integer currentPage, @RequestParam Integer pageSize){
        PageParam pageParam = new PageParam();
        pageParam.setCurrentPage(currentPage);
        pageParam.setPageSize(pageSize);
        return articleService.getArticleListByCategory(categoryId, pageParam);
    }

    @GetMapping("/like")
    @ApiOperation("文章点赞")
    @ControllerLog("点赞")
    @ApiImplicitParam(name = "articleId", value = "文章id")
    public Result<Integer> likeArticle(@RequestParam Integer articleId){
        return articleService.likeArticle(articleId);
    }

}
