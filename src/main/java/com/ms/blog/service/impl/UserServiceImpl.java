package com.ms.blog.service.impl;

import com.ms.blog.common.ErrorCode;
import com.ms.blog.common.Result;
import com.ms.blog.common.aspect.annotation.ServiceLog;
import com.ms.blog.dao.UserMapper;
import com.ms.blog.entity.User;
import com.ms.blog.entity.param.LoginParam;
import com.ms.blog.service.UserService;
import com.ms.blog.util.ResultUtils;
import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 用户业务
 * @author wyh
 * @date 2023/01/10 13:37
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private UserMapper userMapper;

    @Override
    @ServiceLog("登录业务")
    public Result userLogin(LoginParam loginParam) {

        User user = new User();

        if (!redisTemplate.hasKey(loginParam.getCaptcha())){
            return ResultUtils.fail(ErrorCode.CAPTCHA_ERROR.getCode(), ErrorCode.CAPTCHA_ERROR.getMsg());
        }

        if(loginParam.getType() == 0){
            user = userMapper.userLoginByUsername(loginParam.getParams(), loginParam.getPassword());
        }

        if (loginParam.getType() == 1){
            user = userMapper.userLoginByEmail(loginParam.getParams(), loginParam.getPassword());
        }

        if (user == null){
            return ResultUtils.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg()); //新增错误码
        }

        if (user.getStatus() == 1){
            return ResultUtils.fail(ErrorCode.ACCOUNT_NOT_AUTHORIZATION.getCode(), ErrorCode.ACCOUNT_NOT_AUTHORIZATION.getMsg());
        }


        return null;
    }

    @Override
    public Result checkToken(String token) {
        return null;
    }
}
