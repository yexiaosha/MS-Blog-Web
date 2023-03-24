package com.ms.blog.service.impl;

import cn.hutool.core.lang.Validator;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.common.ErrorCode;
import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ServiceLog;
import com.ms.blog.dao.UserMapper;
import com.ms.blog.entity.Cancellation;
import com.ms.blog.entity.User;
import com.ms.blog.entity.UserAuth;
import com.ms.blog.entity.dto.UserDto;
import com.ms.blog.entity.param.CancellationParam;
import com.ms.blog.entity.param.LoginParam;
import com.ms.blog.entity.param.RegisterParam;
import com.ms.blog.entity.param.ResetPasswordParam;
import com.ms.blog.entity.param.UserInfoParam;
import com.ms.blog.entity.param.UserParam;
import com.ms.blog.entity.vo.LoginVo;
import com.ms.blog.entity.vo.UserAuthVo;
import com.ms.blog.entity.vo.UserSimpleVo;
import com.ms.blog.entity.vo.UserVo;
import com.ms.blog.service.CaptchaService;
import com.ms.blog.service.MailService;
import com.ms.blog.service.RoleService;
import com.ms.blog.service.UserService;
import com.ms.blog.util.JwtUtils;
import com.ms.blog.util.Md5Util;
import com.ms.blog.util.ResultUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Resource
    private CaptchaService captchaService;

    @Resource
    private MailService mailService;

    public static final String TOKEN_ = "TOKEN_";

    @Override
    @ServiceLog("用户登录")
    public Result<LoginVo> userLogin(LoginParam loginParam) {
        loginParam.setPassword(Md5Util.encodePassword(loginParam.getPassword()));
        /*Result result = captchaService.verifyCaptcha(loginParam.getCaptcha());
        if (result.getCode() == ErrorCode.CAPTCHA_ERROR.getCode()){
            return ResultUtils.fail(result.getCode(), result.getMessage());
        }*/
        System.out.println(loginParam);
        User user = new User();

        if (Validator.isEmail(loginParam.getAccount())) {
            log.info("邮箱登录");
            user = userMapper.userLoginByEmail(loginParam.getAccount(), loginParam.getPassword());
        }else {
            log.info("用户名登录");
            user = userMapper.userLoginByUsername(loginParam.getAccount(), loginParam.getPassword());
        }

        if (user == null) {
            log.info("账号或密码错误");
            return ResultUtils.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),
                    ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }

        if (user.getStatus() == 1) {
            log.info("用户不存在");
            return ResultUtils.fail(ErrorCode.ACCOUNT_HAS_DISABLED.getCode(), ErrorCode.ACCOUNT_HAS_DISABLED.getMsg());
        }
        userMapper.updateLastLoginTime(new Date(), user.getUsername());
        String token = JwtUtils.createToken(user.getUsername());
        redisTemplate.opsForValue().set(TOKEN_ + token, JSON.toJSONString(user.getUsername()), 15, TimeUnit.DAYS);

        LoginVo loginVo = new LoginVo();
        UserAuth userAuth = userMapper.getUserAuthById(user.getId());
        if (!Objects.isNull(userAuth)){
            UserSimpleVo userSimpleVo = UserSimpleVo.builder()
                    .id(user.getId())
                    .email(userAuth.getEmail())
                    .username(user.getUsername())
                    .avatar(userAuth.getAvatar())
                    .intro(userAuth.getIntro())
                    .nickname(userAuth.getNickname())
                    .website(userAuth.getWebsite())
                    .build();
            loginVo.setUserSimpleVo(userSimpleVo);
        }

        loginVo.setToken(token);
        log.info(loginVo.toString());
        //loginVo.setRole(roleService.getRoleNameByRoleId(user.getRoleId()));
        return ResultUtils.success(loginVo);
    }

    @Override
    @ServiceLog("用户注册")
    @Transactional(noRollbackForClassName = "MalformedJwtException")
    public Result<LoginVo> userRegister(RegisterParam registerParam, UserDto userDto) {
        registerParam.setPassword(Md5Util.encodePassword(registerParam.getPassword()));
        Result<Integer> result = mailService.verifyMailCode(registerParam.getCode());
        if (result.getStatus() == 0) {
            return ResultUtils.fail(ErrorCode.EMAIL_VERIFY_FAIL.getCode(), ErrorCode.EMAIL_VERIFY_FAIL.getMsg());
        }
        //result = captchaService.verifyCaptcha(registerParam.getCaptcha());

        if (userMapper.findUserByUsername(registerParam.getUsername()) != null
                || userMapper.findUserByEmail(registerParam.getEmail()) != null) {
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
                .nickname(registerParam.getNickname())
                .intro("这个用户很神秘，不愿意自我介绍")
                .build();

        userMapper.insertUserAuth(userAuth);
        user.setUserAuthId(userAuth.getId());
        userMapper.insertUserInfo(user);

        LoginVo loginVo = new LoginVo();
        loginVo.setToken(JwtUtils.createToken(user.getUsername()));
        UserSimpleVo userSimpleVo = UserSimpleVo.builder()
                .avatar(userAuth.getAvatar())
                .id(user.getId())
                .email(userAuth.getEmail())
                .intro(userAuth.getIntro())
                .nickname(userAuth.getNickname())
                .username(user.getUsername())
                .website(userAuth.getWebsite())
                .build();
        loginVo.setUserSimpleVo(userSimpleVo);
        return ResultUtils.success(loginVo);
    }

    @Override
    @ServiceLog("用户登出")
    public Result<Boolean> logoutUser(String token) {
        redisTemplate.delete(TOKEN_ + token);
        return ResultUtils.success("登出成功");
    }

    @Override
    @ServiceLog("获取用户基本信息")
    public Result<UserVo> getUserInfo(String username) {
        User user = userMapper.getUserInfo(username);
        UserVo userVo = UserVo.builder().build();
        BeanUtils.copyProperties(user, userVo);
        return ResultUtils.success(userVo);
    }

    @Override
    @ServiceLog("获取用户详细信息")
    public Result<UserAuthVo> getUserInfoDetails(String username) {
        UserAuth userAuth = userMapper.getUserInfoDetails(username);
        UserAuthVo userAuthVo = UserAuthVo.builder().build();
        BeanUtils.copyProperties(userAuth, userAuthVo);
        return ResultUtils.success(userAuthVo);
    }

    @Override
    @ServiceLog("更改用户详细信息")
    public Result<UserSimpleVo> updateUserDetailInfo(UserInfoParam userInfoParam) {
        //获取已存在的详情id
        UserAuth userAuthById = userMapper.getUserAuthById(userInfoParam.getId());
        if (Objects.isNull(userAuthById)){
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        UserAuth userAuth = UserAuth.builder()
                .intro(userInfoParam.getIntro())
                .updateTime(new Date())
                .nickname(userInfoParam.getNickname())
                .website(userInfoParam.getWebsite())
                .id(userAuthById.getId())
                .build();
        userMapper.updateUserInfoDetails(userAuth);
        UserAuth userInfoDetails = userMapper.getUserInfoDetails(userInfoParam.getUsername());
        UserSimpleVo userSimpleVo = UserSimpleVo.builder()
                .website(userInfoParam.getWebsite())
                .username(userInfoParam.getUsername())
                .id(userInfoDetails.getId())
                .nickname(userInfoParam.getNickname())
                .intro(userInfoParam.getIntro())
                .email(userInfoDetails.getEmail())
                .avatar(userInfoDetails.getAvatar())
                .build();
        return ResultUtils.success(userSimpleVo);
    }

    @Override
    @ServiceLog("获取所有用户列表")
    public Result<PageData<UserVo>> getUserList(UserParam userParam) {
        Page<User> page = new Page<>(userParam.getCurrentPage(), userParam.getPageSize());

        @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
        IPage<User> userIPage = userMapper.getUserList(userParam, page);

        List<UserVo> userVoList = new ArrayList<>();
        UserVo userVo = UserVo.builder().build();
        for (User user : userIPage.getRecords()) {
            BeanUtils.copyProperties(user, userVo);
            userVoList.add(userVo);
        }
        PageData<UserVo> userVoPageData = new PageData<>(userVoList, userIPage.getTotal(), userIPage.getPages(),
                userIPage.getCurrent());
        return ResultUtils.success(userVoPageData);
    }

    @Override
    @ServiceLog("重置密码")
    public Result<Integer> resetPassword(ResetPasswordParam resetPasswordParam) {

        if (userMapper.findUserByEmail(resetPasswordParam.getEmail()) == null
                && userMapper.findUserByUsername(resetPasswordParam.getUsername()) == null) {
            return ResultUtils.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),
                    ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }

        Result<Integer> result = mailService.verifyMailCode(resetPasswordParam.getCode());

        if (result.getStatus() != 1) {
            return result;
        }

        if (redisTemplate.opsForValue().get(TOKEN_ + resetPasswordParam.getToken()) != null) {
            redisTemplate.delete(TOKEN_ + resetPasswordParam.getToken());
        }

        Integer id = userMapper.getUserIdByEmail(resetPasswordParam.getEmail());

        return ResultUtils.success(userMapper.updateUserPasswordById(id, resetPasswordParam.getPassword(), new Date()));
    }

    @Override
    @ServiceLog("提交注销表单")
    public Result<Integer> cancelUserAccount(CancellationParam cancellationParam, String token) {
        User user = userMapper.findUserByUsername(cancellationParam.getUsername());
        if (user == null) {
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        Cancellation cancellation = Cancellation.builder()
                .reasonText(cancellationParam.getReasonText())
                .userId(user.getId())
                .username(cancellationParam.getUsername())
                .email(cancellationParam.getEmail())
                .createTime(new Date())
                .build();

        userMapper.insertUserCancellation(cancellation);
        userMapper.updateUserStatus(user.getId(), 0);
        redisTemplate.delete(TOKEN_ + token);
        return ResultUtils.success("提交成功");
    }

    @Override
    @ServiceLog("token检查")
    public Result<User> checkToken(String token) {

        Map<String, Object> map = JwtUtils.verifyToken(token);
        if (map == null) {
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        String userJson = redisTemplate.opsForValue().get(TOKEN_ + token);
        if (!(StringUtils.hasText(userJson))) {
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        return ResultUtils.success("检查通过", JSON.parseObject(userJson, User.class));
    }

    @Override
    @ServiceLog("根据用户id获取用户详情")
    public UserAuth getUserInfoDetailsByUserId(Integer userId) {
        return userMapper.getUserAuthById(userId);
    }

    @Override
    @ServiceLog("根据用户id获取用户")
    public User getUserByUserId(Integer userId) {
        return userMapper.getUserById(userId);
    }
}