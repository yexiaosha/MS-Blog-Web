package com.ms.blog.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.entity.Cancellation;
import com.ms.blog.entity.User;
import com.ms.blog.entity.UserAuth;
import com.ms.blog.entity.param.UserParam;
import java.util.Date;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户访问接口
 * @author wyh
 * @date 2023/01/10 18:05
 */
@Mapper
public interface UserMapper {

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return 用户
     */
   User findUserByUsername(String username);

    /**
     * 通过邮箱查找用户
     * @param email 邮箱
     * @return  用户
     */
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

    /**
     * 获取用户基本信息
     * @param username 用户名
     * @return 用户基本信息
     */
   User getUserInfo(String username);

    /**
     * 获取用户信息详情
     * @param username 用户名
     * @return 用户详情信息
     */
   UserAuth getUserInfoDetails(String username);

    /**
     * 获取用户列表
     * @param userParam 用户字段
     * @param userPage  用户类分页
     * @return  当前页用户分类
     */
   IPage<User> getUserList(UserParam userParam, Page<User> userPage);

   int insertUserCancellation(Cancellation cancellation);

   int updateUserInfo(User user);
   int updateUserInfoDetails(UserAuth userAuth);
}
