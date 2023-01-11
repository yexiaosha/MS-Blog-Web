package com.ms.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.ms.blog.common.ErrorCode;
import com.ms.blog.common.Result;
import com.ms.blog.common.aspect.annotation.ServiceLog;
import com.ms.blog.dao.UserMapper;
import com.ms.blog.entity.User;
import com.ms.blog.entity.UserAuth;
import com.ms.blog.entity.dto.UserDto;
import com.ms.blog.entity.param.LoginParam;
import com.ms.blog.entity.param.RegisterParam;
import com.ms.blog.entity.vo.LoginVo;
import com.ms.blog.service.RoleService;
import com.ms.blog.service.UserService;
import com.ms.blog.util.JwtUtils;
import com.ms.blog.util.Md5Util;
import com.ms.blog.util.ResultUtils;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户业务
 * @author wyh
 * @date 2023/01/10 13:37
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleService roleService;

    public static final String CAPTCHA_ = "CAPTCHA_";

    public static final String TOKEN_ = "TOKEN_";

    @Override
    @ServiceLog("登录业务")
    public Result<LoginVo> userLogin(LoginParam loginParam) {
        loginParam.setPassword(Md5Util.encodePassword(loginParam.getPassword()));
        String captchaKey = redisTemplate.opsForValue().get(CAPTCHA_ + loginParam.getCaptcha());
        User user = new User();
        if (captchaKey == null){
            return ResultUtils.fail(ErrorCode.CAPTCHA_ERROR.getCode(), ErrorCode.CAPTCHA_ERROR.getMsg());
        }

        if(loginParam.getType() == 0){
            user = userMapper.userLoginByUsername(loginParam.getParams(), loginParam.getPassword());
            redisTemplate.delete(captchaKey);
        }

        if (loginParam.getType() == 1){
            user = userMapper.userLoginByEmail(loginParam.getParams(), loginParam.getPassword());
            redisTemplate.delete(captchaKey);
        }

        if (user == null){
            return ResultUtils.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }

        if (user.getStatus() == 1){
            return ResultUtils.fail(ErrorCode.ACCOUNT_HAS_DISABLED.getCode(), ErrorCode.ACCOUNT_HAS_DISABLED.getMsg());
        }
        userMapper.updateLastLoginTime(new Date(), user.getUsername());
        String token = JwtUtils.createToken(user.getUsername());
        redisTemplate.opsForValue().set(TOKEN_+token, JSON.toJSONString(user),1, TimeUnit.DAYS);

        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRole(roleService.getRoleNameByRoleId(user.getRoleId()));
        return ResultUtils.success(loginVo);
    }

    @Override
    public Result<Integer> userRegister(RegisterParam registerParam, UserDto userDto) {
        if (redisTemplate.opsForValue().get(CAPTCHA_ + registerParam.getCaptcha()) == null){
            return ResultUtils.fail(ErrorCode.CAPTCHA_ERROR.getCode(), ErrorCode.CAPTCHA_ERROR.getMsg());
        }

        if (userMapper.findUserByUsername(registerParam.getUsername()) == null
                || userMapper.findUserByEmail(registerParam.getEmail()) == null){
            return ResultUtils.fail(ErrorCode.ACCOUNT_HAS_EXIST.getCode(), ErrorCode.ACCOUNT_HAS_EXIST.getMsg());
        }

        User user = User.builder()
                .browser(userDto.getBrowser())
                .createTime(new Date())
                .ipAddress(userDto.getIpAddress())
                .ipSource(userDto.getIpSource())
                .lastLoginTime(new Date())
                .os(userDto.getOs())
                .loginType(0)
                .roleId(0)
                .status(0)
                .password(registerParam.getPassword())
                .username(registerParam.getUsername())
                .updateTime(new Date())
                .build();
        UserAuth userAuth = UserAuth.builder()
                .email(registerParam.getEmail())
                .isDisabled(0)
                .nikeName(registerParam.getNikeName())
                .build();

        Integer authId = userMapper.insertUserAuth(userAuth);
        user.setUserAuthId(authId);

        return ResultUtils.success(userMapper.insertUserInfo(user));
    }

    @Override
    public Result<String> checkToken(String token) {

        Map<String, Object> map = JwtUtils.verifyToken(token);
        if (map == null) {
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        String userJson = redisTemplate.opsForValue().get(TOKEN_ + token);
        if (!(StringUtils.hasText(userJson))) {
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        return ResultUtils.success(JSON.parseObject(userJson, LoginParam.class).getParams());
    }

}
