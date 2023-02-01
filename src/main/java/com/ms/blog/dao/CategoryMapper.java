package com.ms.blog.dao;

import com.ms.blog.entity.Category;
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
}
