package com.ms.blog.service;

import com.ms.blog.common.Result;
import com.ms.blog.entity.param.CategoryParam;
import com.ms.blog.entity.vo.CategoryVo;
import java.util.List;


/**
 * 分类管理业务接口
 * @author wyh
 * @date 2023/01/30 18:09
 */
public interface CategoryService {

    /**
     * 根据分类名获取所有分类信息
     * @param categoryName 分类名
     * @return 通用返回
     */
    Result<CategoryVo> getCategoryByName(String categoryName);

    /**
     * 根据id获取分类
     * @param id    分类id
     * @return  分类实体
     */
    Result<CategoryVo> getCategoryById(Integer id);

    /**
     * 获取所有分类
     * @return 分类列表
     */
    Result<List<CategoryVo>> getCategoryList();

    /**
     * 删除分类
     * @param id 分类id
     * @return 结果
     */
    Result<Integer> deleteCategory(Integer id);

    /**
     * 新增分类
     * @param categoryParam 分类参数
     * @return  结果
     */
    Result<Integer> insertCategory(CategoryParam categoryParam);

    /**
     * 修改分类
     * @param categoryParam 分类参数
     * @return 结果
     */
    Result<Integer> updateCategory(CategoryParam categoryParam);

    /**
     *  获取热门文章
     * @return 热门文章列表
     */
    Result<List<CategoryVo>> getPopularCategory();
}
