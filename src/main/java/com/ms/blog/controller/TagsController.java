package com.ms.blog.controller;

import com.ms.blog.common.PageData;
import com.ms.blog.common.PageParam;
import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ControllerLog;
import com.ms.blog.entity.param.TagParam;
import com.ms.blog.entity.vo.TagVo;
import com.ms.blog.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签接口
 * @author wyh
 * @date 2023/01/06 13:56
 */
@RestController
@Tag(name = "标签接口")
@RequestMapping("/msblog/tags")
@CrossOrigin
public class TagsController {

    @Resource
    private TagService tagService;

    @PostMapping("/list")
    @Operation(description = "分页查找所有标签")
    @ControllerLog("分页查找所有标签")
    public Result<PageData<TagVo>> getTagList(@RequestBody PageParam pageParam){
        return tagService.getTagList(pageParam);
    }

    @GetMapping("/delete/{id}")
    @Operation(description = "删除标签")
    @ControllerLog("删除标签")
    public Result<Integer> deleteTags(@PathVariable("id") Integer id){
        return tagService.deleteTag(id);
    }

    @PostMapping("/insert")
    @Operation(description = "插入标签")
    @ControllerLog("插入标签")
    public Result<Integer> insertTag(@RequestBody TagParam tagParam){
        return tagService.insertTag(tagParam);

    }

    @GetMapping("/popular")
    @Operation(description = "获取热门标签")
    @ControllerLog("获取热门标签")
    public Result<List<TagVo>> getPopularTagList(){
        return tagService.getPopularTagList();
    }
}
