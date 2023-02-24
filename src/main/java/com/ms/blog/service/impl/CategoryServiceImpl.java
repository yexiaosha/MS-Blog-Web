package com.ms.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ServiceLog;
import com.ms.blog.dao.CategoryMapper;
import com.ms.blog.entity.Category;
import com.ms.blog.entity.param.CategoryParam;
import com.ms.blog.entity.vo.CategoryVo;
import com.ms.blog.service.CategoryService;
import com.ms.blog.util.ResultUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 分类管理业务接口实现
 * @author wyh
 * @date 2023/01/30 18:13
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public static final String HOT_CATEGORY_LIST = "HOT_CATEGORY_LIST_";

    @Override
    @ServiceLog("通过分类名获取分类信息")
    public Result<CategoryVo> getCategoryByName(String categoryName) {
        Category category = categoryMapper.getCategoryByName(categoryName);
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return ResultUtils.success(categoryVo);
    }

    @Override
    @ServiceLog("根据id获取分类")
    public Result<CategoryVo> getCategoryById(Integer id) {
        Category category = categoryMapper.getCategoryById(id);
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return ResultUtils.success(categoryVo);
    }

    @Override
    @ServiceLog("获取所有分类")
    public Result<List<CategoryVo>> getCategoryList() {
        List<Category> categoryList = categoryMapper.getCategoryList();
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (Category c : categoryList) {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(c, categoryVo);
            categoryVoList.add(categoryVo);
        }
        return ResultUtils.success(categoryVoList);
    }

    @Override
    @ServiceLog("删除分类")
    public Result<Integer> deleteCategory(Integer id) {
        return ResultUtils.success(categoryMapper.deleteCategory(id));
    }

    @Override
    @ServiceLog("新增分类")
    public Result<Integer> insertCategory(CategoryParam categoryParam) {
        Category category = Category.builder()
                .createTime(new Date())
                .name(categoryParam.getName())
                .updateTime(new Date())
                .build();

        return ResultUtils.success(categoryMapper.insertCategory(category));
    }

    @Override
    @ServiceLog("修改分类")
    public Result<Integer> updateCategory(CategoryParam categoryParam) {
        Category category = Category.builder()
                .updateTime(new Date())
                .name(categoryParam.getName())
                .id(categoryParam.getId())
                .build();
        return ResultUtils.success(categoryMapper.updateCategory(category));
    }

    @Override
    @ServiceLog("获取热门文章")
    public Result<List<CategoryVo>> getPopularCategory() {
        return ResultUtils.success(JSON.parseArray(redisTemplate.opsForValue().get(HOT_CATEGORY_LIST), CategoryVo.class));
    }
}
