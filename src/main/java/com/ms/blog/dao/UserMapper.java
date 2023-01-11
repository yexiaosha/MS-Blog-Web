package com.ms.blog.dao;

import com.ms.blog.entity.User;
import com.ms.blog.entity.UserAuth;
import java.util.Date;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户访问接口
 * @author wyh
 * @date 2023/01/10 18:05
 */
@Mapper
public interface UserMapper {


   User findUserByUsername(String username);


   User findUserByEmail(String email);

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

    /**
     * 插入用户详情信息
     * @param userAuth 用户详情信息类
     * @return 用户详情信息主键id
     */
   Integer insertUserAuth(UserAuth userAuth);

    /**
     * 插入用户基本信息
     * @param user 用户类
     * @return 是否成功
     */
   int insertUserInfo(User user);

    /**
     * 更新用户最后登录时间
     * @param date 时间
     * @param username 用户名
     * @return 是否成功
     */
   int updateLastLoginTime(Date date, String username);
}
