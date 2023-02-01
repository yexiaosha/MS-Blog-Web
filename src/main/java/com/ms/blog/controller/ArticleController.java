package com.ms.blog.controller;

import com.alibaba.fastjson.JSON;
import com.ms.blog.common.ErrorCode;
import com.ms.blog.common.PageData;
import com.ms.blog.common.PageParam;
import com.ms.blog.common.Result;
import com.ms.blog.common.aspect.annotation.ControllerLog;
import com.ms.blog.entity.User;
import com.ms.blog.entity.param.ArticleConditionParam;
import com.ms.blog.entity.param.ArticleParam;
import com.ms.blog.entity.vo.ArticleVo;
import com.ms.blog.service.ArticleService;
import com.ms.blog.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章接口
 * @author wyh
 * @date 2023/01/05 17:57
 */

@RestController
@Api(tags = "文章接口")
@RequestMapping("/msblog/article")
@Validated
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @PostMapping("/list")
    @ControllerLog("获取所有文章列表")
    @ApiOperation("获取所有文章列表")
    public Result<PageData<ArticleVo>> getAllArticleList(@RequestBody PageParam pageParam){
        return articleService.getArticleList(pageParam);
    }

    @PostMapping("/list/hot")
    @ApiOperation("获取热门文章")
    @ControllerLog("获取热门文章")
    public Result<List<ArticleVo>> getPopularArticleList(){
        return articleService.getPopularArticleList();
    }

    @GetMapping("/content/{id}")
    @ApiOperation("获取文章详情")
    @ControllerLog("获取文章详情")
    @ApiImplicitParams(
            @ApiImplicitParam(value = "文章id", name = "id")
    )
    public Result<ArticleVo> getArticleContent(@PathVariable("id") @NotBlank Integer id){
        return articleService.getArticleContent(id);
    }

    @PostMapping("/List/conditions")
    @ApiOperation("根据某一条件获取相应文章")
    @ControllerLog("根据某一条件获取相应文章")
    public Result<PageData<ArticleVo>> getArticleListByType(@RequestBody ArticleConditionParam articleConditionParam){
        return articleService.getArticleListByType(articleConditionParam);
    }

    @DeleteMapping("/delete")
    @ApiOperation("批量删除文章")
    @ControllerLog("批量删除文章")
    @ApiImplicitParams(
            @ApiImplicitParam(value = "文章id", name = "articleId")
    )
    public Result<Integer> deleteArticles(@RequestParam Integer articleId){
        return null;
    }

    @PostMapping("/insert")
    @ApiOperation("新增文章")
    @ControllerLog("新增文章")
    public Result<Integer> insertArticle(@RequestBody ArticleParam articleParam, @RequestHeader("Authorization")String token ){
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        User user = JSON.parseObject(userJson, User.class);
        if (user == null){
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        return articleService.insertArticle(articleParam, user.getId());
    }

    @PostMapping("/temporary")
    @ApiOperation("暂存文章")
    @ControllerLog("暂存文章")
    public Result<Integer> temporaryArticle(@RequestBody ArticleParam articleParam, @RequestHeader("Authorization")String token){
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        User user = JSON.parseObject(userJson, User.class);
        if (user == null){
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        return articleService.temporaryArticle(articleParam, user.getId());
    }

    @PostMapping("/update")
    @ApiOperation("更改文章")
    @ControllerLog("更改文章")
    public Result<Integer> updateArticle(@RequestBody ArticleParam articleParam){
        return articleService.updateArticle(articleParam);
    }

    @PostMapping("/tags")
    @ApiOperation("获取文章所有的标签")
    @ControllerLog("获取文章所有的标签")
    public Result getArticleTags(){return null;}
}