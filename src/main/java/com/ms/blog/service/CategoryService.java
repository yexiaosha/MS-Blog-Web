package com.ms.blog.service;

import com.ms.blog.common.Result;
import com.ms.blog.entity.vo.CategoryVo;


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

    Result<CategoryVo> getCategoryById(Integer id);
}
