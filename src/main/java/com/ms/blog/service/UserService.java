package com.ms.blog.service;

import com.ms.blog.common.Result;
import com.ms.blog.entity.param.LoginParam;

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
    Result userLogin(LoginParam loginParam);

    /**
     * token检查
     * @param token jwttoken
     * @return 通用结果
     */
    Result<String> checkToken(String token);
}
