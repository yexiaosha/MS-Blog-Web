package com.ms.blog.service;

import com.ms.blog.common.Result;
import com.ms.blog.entity.dto.UserDto;
import com.ms.blog.entity.param.LoginParam;
import com.ms.blog.entity.param.RegisterParam;
import com.ms.blog.entity.vo.LoginVo;

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
     * @return 通用结果
     */
    Result<Integer> userRegister(RegisterParam registerParam, UserDto userDto);

    /**
     * token检查
     * @param token jwttoken
     * @return 通用结果
     */
    Result<String> checkToken(String token);
}
