package com.ms.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.entity.Article;
import com.ms.blog.entity.Cancellation;
import com.ms.blog.entity.User;
import com.ms.blog.entity.UserAuth;
import com.ms.blog.entity.param.UserParam;
import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户访问接口
 * @author wyh
 * @date 2023/01/10 18:05
 */
@Mapper
public interface UserMapper extends BaseMapper<Article> {

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return 用户
     */
   User findUserByUsername(@Param("username") String username);

    /**
     * 通过邮箱查找用户
     * @param email 邮箱
     * @return  用户
     */
   User findUserByEmail(@Param("email") String email);

    /**
     * 通过用户名登录
     * @param username  用户名
     * @param password  密码
     * @return  返回成功的用户名
     */
   User userLoginByUsername(@Param("username") String username, @Param("password") String password);

    /**
     * 通过邮箱登录
     * @param email     邮箱
     * @param password  密码
     * @return  返回成功用户名
     */
   User userLoginByEmail(@Param("email") String email, @Param("password") String password);

    /**
     * 插入用户详情信息
     * @param userAuth 用户详情信息类
     * @return 用户详情信息主键id
     */
   int insertUserAuth(UserAuth userAuth);

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
   int updateLastLoginTime(Date date, @Param("username") String username);

    /**
     * 获取用户基本信息
     * @param username 用户名
     * @return 用户基本信息
     */
   User getUserInfo(@Param("username") String username);

    /**
     * 获取用户信息详情
     * @param username 用户名
     * @return 用户详情信息
     */
   UserAuth getUserInfoDetails(@Param("username") String username);

    /**
     * 获取用户列表
     * @param userParam 用户字段
     * @param userPage  用户类分页
     * @return  当前页用户分类
     */
   IPage<User> getUserList(UserParam userParam, Page<User> userPage);

    /**
     * 插入注销申请
     * @param cancellation 注销申请表类
     * @return 是否成功
     */
   int insertUserCancellation(Cancellation cancellation);

    /**
     * 更改用户账号状态
     * @param id 用户id
     * @param status 用户状态值
     * @return 是否成功
     */
   int updateUserStatus(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * 更改用户详细信息
     * @param userAuth 用户详细信息类
     * @return 是否成功
     */
   int updateUserInfoDetails(UserAuth userAuth);

    /**
     * 根据用户id获取用户基本信息
     * @param id 用户id
     * @return 用户实体
     */
   User getUserById(@Param("id") Integer id);

    /**
     * 根据用户id获取用户详情
     * @param id 用户id
     * @return  用户详情
     */
   UserAuth getUserAuthById(@Param("id") Integer id);

    /**
     * 通过邮箱更该用户密码
     * @param id 邮箱
     * @param password 密码
     * @return 结果
     */
   int updateUserPasswordById(@Param("id") Integer id, @Param("password") String password, @Param("updateTime") Date updateTime);

    /**
     * 通过邮箱获取用户id
     * @param email 邮箱
     * @return 结果
     */
    Integer getUserIdByEmail(@Param("email") String email);
}
