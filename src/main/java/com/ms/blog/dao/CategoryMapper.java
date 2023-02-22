package com.ms.blog.dao;

import com.ms.blog.entity.Category;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类接口
 * @author wyh
 * @date 2023/02/01 17:27
 */
@Mapper
public interface CategoryMapper {

    /**
     * 通过分类id获取分类
     * @param id 主键id
     * @return 分类对象
     */
    Category getCategoryById(Integer id);

    /**
     * 通过分类名查找分类
     * @param name 分类名
     * @return 结果
     */
    Category getCategoryByName(String name);

    /**
     * 查找所有分类
     * @return 结果
     */
    List<Category> getCategoryList();

    /**
     * 删除分类
     * @param id 分类id
     * @return 结果
     */
    int deleteCategory(Integer id);

    /**
     * 新增分类
     * @param category 分类实体
     * @return 结果
     */
    int insertCategory(Category category);

    /**
     * 更改分类
     * @param category 分类
     * @return 结果
     */
    int updateCategory(Category category);

    /**
     * 更新分类点击量
     * @param id    分类id
     * @param clickVolume   点击量
     * @return 结果
     */
    int updateCategoryClickVolume(Integer id, Integer clickVolume);
}
