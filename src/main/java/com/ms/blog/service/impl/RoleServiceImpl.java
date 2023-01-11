package com.ms.blog.service.impl;

import com.ms.blog.dao.RoleMapper;
import com.ms.blog.service.RoleService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 角色类型业务接口实现
 * @author wyh
 * @date 2023/01/11 17:12
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public String getRoleNameByRoleId(Integer roleId) {
        return roleMapper.getRoleNameByRoleId(roleId);
    }
}
