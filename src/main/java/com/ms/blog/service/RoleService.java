package com.ms.blog.service;

/**
 * 角色类型业务接口
 * @author wyh
 * @date 2023/01/11 17:10
 */
public interface RoleService {

    /**
     * 通过角色类型id获取角色类型名称
     * @param roleId 角色类型id
     * @return 角色类型名称
     */
    String getRoleNameByRoleId(Integer roleId);

}
