package com.ms.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ms.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色类型接口
 * @author wyh
 * @date 2023/01/11 17:13
 */
@Mapper
public interface RoleMapper extends BaseMapper<Article> {

    /**
     * 通过角色类型id获取角色类型名称
     * @param roleId 角色类型id
     * @return 角色类型名称
     */
    String getRoleNameByRoleId(Integer roleId);
}
