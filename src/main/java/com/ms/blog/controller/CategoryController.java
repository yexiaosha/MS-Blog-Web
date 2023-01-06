package com.ms.blog.controller;

import com.ms.blog.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分类接口
 * @author wyh
 * @date 2023/01/05 18:05
 */
@RestController
@Api(tags = "分类接口")
@RequestMapping("/msblog/category")
public class CategoryController {

    @GetMapping("/List")
    @ApiModelProperty("获取分类")
    public Result getCategoryList(){
        return null;
    }

    @DeleteMapping("/delete")
    @ApiModelProperty("删除分类")
    public Result deleteCategory(){
        return null;
    }

    @PostMapping("/insert")
    @ApiModelProperty("新增分类")
    public Result insertCategory(){
        return null;
    }

    @PostMapping("/update")
    @ApiModelProperty("修改分类")
    public Result updateCategory(){
        return null;
    }
}
