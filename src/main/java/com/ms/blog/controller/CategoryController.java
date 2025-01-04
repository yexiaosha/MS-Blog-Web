package com.ms.blog.controller;

import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ControllerLog;
import com.ms.blog.entity.param.CategoryParam;
import com.ms.blog.entity.vo.CategoryVo;
import com.ms.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类接口
 * @author wyh
 * @date 2023/01/05 18:05
 */
@RestController
@Tag(name = "分类接口")
@RequestMapping("/msblog/category")
@CrossOrigin
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    @Operation(description = "获取分类")
    @ControllerLog("获取所有分类")
    public Result<List<CategoryVo>> getCategoryList(){
        return categoryService.getCategoryList();
    }

    @GetMapping("/delete/{id}")
    @Operation(description = "删除分类")
    @ControllerLog("删除分类")
    public Result<Integer> deleteCategory(@PathVariable("id") Integer id){
        return categoryService.deleteCategory(id);
    }

    @PostMapping("/insert")
    @Operation(description = "新增分类")
    @ControllerLog("新增分类")
    public Result<Integer> insertCategory(@RequestBody CategoryParam categoryParam){
        return categoryService.insertCategory(categoryParam);
    }

    @PostMapping("/update")
    @Operation(description = "修改分类")
    @ControllerLog("修改分类")
    public Result<Integer> updateCategory(@RequestBody CategoryParam categoryParam){
        return categoryService.updateCategory(categoryParam);
    }

    @GetMapping("/popular")
    @Operation(description = "获取热门文章")
    @ControllerLog("获取热门文章")
    public Result<List<CategoryVo>> getPopularCategory(){
        return categoryService.getPopularCategory();

    }
}
