package com.ms.blog.controller;

import com.ms.blog.common.Result;
import com.ms.blog.entity.param.ArticleConditionParam;
import com.ms.blog.entity.param.ArticleParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/List")
    @ApiOperation("获取所有文章列表")
    public Result getAllArticleList(){
        return null;
    }

    @GetMapping("/content/{id}")
    @ApiOperation("获取文章详情")
    public Result getArticleContent(@PathVariable("id") @NotBlank Integer id){
        return null;
    }

    @PostMapping("/List/conditions")
    @ApiOperation("根据某一条件获取相应文章")
    public Result getArticleListByType(@RequestBody ArticleConditionParam articleConditionParam){
        return null;
    }

    @PostMapping("/search")
    @ApiOperation("关键字搜索查找文章")
    public Result searchArticle(){
        return null;
    }

    @DeleteMapping ("/delete/{id}")
    @ApiOperation("删除文章")
    public Result deleteArticle(@PathVariable("id") @NotBlank Integer id){
        return null;
    }

    @DeleteMapping("/deleteList")
    @ApiOperation("批量删除文章")
    public Result deleteArticleList(@RequestBody @NotEmpty List<Integer> articleList){
        return null;
    }

    @PostMapping("/insert")
    @ApiOperation("新增文章")
    public Result insertArticle(@RequestBody ArticleParam articleParam){
        return null;
    }

    @PostMapping("/temporary")
    @ApiModelProperty("暂存文章")
    public Result temporaryArticle(@RequestBody ArticleParam articleParam){
        return null;
    }

    @PostMapping("/update")
    @ApiModelProperty("更改文章")
    public Result updateArticle(){
        return null;
    }
}