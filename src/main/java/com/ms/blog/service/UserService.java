package com.ms.blog.service;

import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.entity.dto.UserDto;
import com.ms.blog.entity.param.CancellationParam;
import com.ms.blog.entity.param.LoginParam;
import com.ms.blog.entity.param.RegisterParam;
import com.ms.blog.entity.param.ResetPasswordParam;
import com.ms.blog.entity.param.UserInfoParam;
import com.ms.blog.entity.param.UserParam;
import com.ms.blog.entity.vo.LoginVo;
import com.ms.blog.entity.vo.UserAuthVo;
import com.ms.blog.entity.vo.UserVo;

/**
 * 用户业务接口
 * @author wyh
 * @date 2023/01/10 12:02
 */
public interface UserService {

    /**
     *  用户登录
     * @param loginParam 登录参数
     * @return 通用结果
     */
    Result<LoginVo> userLogin(LoginParam loginParam);

    /**
     * 游客注册
     * @param registerParam 注册参数
     * @param userDto 用户数据传输类
     * @return 通用结果
     */
    Result<Integer> userRegister(RegisterParam registerParam, UserDto userDto);

    /**
     * 用户登出
     * @param token 令牌
     * @return 登出结果
     */
    Result<Boolean> logoutUser(String token);

    /**
     * 获取用户基本信息
     * @param username 用户名
     * @return 用户信息视图类
     */
    Result<UserVo> getUserInfo(String username);

    /**
     * 获取用户详细信息
     * @param username 用户名
     * @return 用户详细信息视图类
     */
    Result<UserAuthVo> getUserInfoDetails(String username);

    /**
     * 更改用户详细信息
     * @param userInfoParam 用户详细信息参数
     * @return 是否更改
     */
    Result<Integer> updateUserDetailInfo(UserInfoParam userInfoParam);

    /**
     * 获取所有用户列表
     * @param userParam 用户查找表单
     * @return 用户信息列表
     */
    Result<PageData<UserVo>> getUserList(UserParam userParam);

    /**
     * 重置密码/忘记密码
     * @param resetPasswordParam 忘记密码表单
     * @param token 令牌
     * @return 结果
     */
    Result<Integer> resetPassword(ResetPasswordParam resetPasswordParam, String token);

    /**
     * 注销请求提交
     * @param cancellationParam 注销提交表单
     * @param token 令牌
     * @return  是否请求成功
     */
    Result<Integer> cancelUserAccount(CancellationParam cancellationParam, String token);

    /**
     * token检查
     * @param token jwttoken
     * @return 通用结果
     */
    Result<String> checkToken(String token);
}
