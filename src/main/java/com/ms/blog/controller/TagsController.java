package com.ms.blog.controller;

import com.ms.blog.common.PageData;
import com.ms.blog.common.PageParam;
import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ControllerLog;
import com.ms.blog.entity.param.TagParam;
import com.ms.blog.entity.vo.TagVo;
import com.ms.blog.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

/**
 * 标签接口
 * @author wyh
 * @date 2023/01/06 13:56
 */
@RestController
@Api(tags = "标签接口")
@RequestMapping("/msblog/tags")
@CrossOrigin
public class TagsController {

    @Resource
    private TagService tagService;

    @PostMapping("/list")
    @ApiOperation("查找所有标签")
    @ControllerLog("查找所有标签")
    public Result<PageData<TagVo>> getTagList(@RequestBody PageParam pageParam){
        return tagService.getTagList(pageParam);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除标签")
    @ControllerLog("删除标签")
    public Result<Integer> deleteTags(@PathVariable("id") Integer id){
        return tagService.deleteTag(id);
    }

    @PostMapping("/insert")
    @ApiOperation("插入标签")
    @ControllerLog("插入标签")
    public Result<Integer> insertTag(@RequestBody TagParam tagParam){
        return tagService.insertTag(tagParam);

    }

    @GetMapping("/popular")
    @ApiOperation("获取热门标签")
    @ControllerLog("获取热门标签")
    public Result<List<TagVo>> getPopularTagList(){
        return tagService.getPopularTagList();
    }
}
