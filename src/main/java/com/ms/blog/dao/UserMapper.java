package com.ms.blog.dao;

import com.ms.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户访问接口
 * @author wyh
 * @date 2023/01/10 18:05
 */
@Mapper
public interface UserMapper {

    /**
     * 通过用户名登录
     * @param username  用户名
     * @param password  密码
     * @return  返回成功的用户名
     */
   User userLoginByUsername(String username, String password);

    /**
     * 通过邮箱登录
     * @param email     邮箱
     * @param password  密码
     * @return  返回成功用户名
     */
   User userLoginByEmail(String email, String password);
}
