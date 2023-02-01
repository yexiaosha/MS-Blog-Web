package com.ms.blog.service.impl;

import com.ms.blog.common.Result;
import com.ms.blog.common.aspect.annotation.ServiceLog;
import com.ms.blog.entity.vo.CategoryVo;
import com.ms.blog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 分类管理业务接口实现
 * @author wyh
 * @date 2023/01/30 18:13
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Override
    @ServiceLog("通过分类名获取分类信息")
    public Result<CategoryVo> getCategoryByName(String categoryName) {
        return null;
    }

    @Override
    public Result<CategoryVo> getCategoryById(Integer id) {
        return null;
    }
}
