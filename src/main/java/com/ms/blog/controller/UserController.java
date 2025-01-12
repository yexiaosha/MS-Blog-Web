package com.ms.blog.controller;

import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ControllerLog;
import com.ms.blog.entity.dto.UserDto;
import com.ms.blog.entity.param.*;
import com.ms.blog.entity.vo.LoginVo;
import com.ms.blog.entity.vo.UserAuthVo;
import com.ms.blog.entity.vo.UserSimpleVo;
import com.ms.blog.entity.vo.UserVo;
import com.ms.blog.service.UserService;
import com.ms.blog.util.IpUtils;
import com.ms.blog.util.SystemUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口
 * @author wyh
 * @date 2023/01/06 14:03
 */
@RestController
@Tag(name = "用户接口")
@RequestMapping("/msblog/user")
@Slf4j
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    @Operation(description = "用户登录")
    @ControllerLog("用户登录")
    public Result<LoginVo> userLogin(@RequestBody LoginParam loginParam){
        return userService.userLogin(loginParam);
    }

    @GetMapping("/info/{username}")
    @Operation(description = "获取用户基础信息")
    @ControllerLog("获取用户基础信息")
    @Deprecated
    public Result<UserVo> getUserInfo(@PathVariable("username") String username){
        return userService.getUserInfo(username);
    }

    @GetMapping("/info/detail/{username}")
    @Operation(description = "获取用户详情")
    @ControllerLog("获取用户详情")
    @Deprecated
    public Result<UserAuthVo> getUserInfoDetails(@PathVariable("username") String username){
        return userService.getUserInfoDetails(username);
    }

    @PostMapping("/info/detail/update")
    @Operation(description = "更改用户信息")
    @ControllerLog("更改用户信息")
    public Result<UserSimpleVo> updateUserDetailInfo(@RequestBody UserInfoParam userInfoParam){
        log.info(userInfoParam.toString());
        return userService.updateUserDetailInfo(userInfoParam);

    }

    @PostMapping("/list")
    @Operation(description = "获取用户列表")
    @ControllerLog("获取用户列表")
    public Result<PageData<UserVo>> getUserList(@RequestBody UserParam userParam){
        return userService.getUserList(userParam);
    }

    @DeleteMapping("/cancellation")
    @Operation(description = "用户注销")
    @ControllerLog("用户注销")
    public Result<Integer> cancellationUser(@RequestBody CancellationParam cancellationParam, @RequestHeader("Authorization") String token){
        return userService.cancelUserAccount(cancellationParam, token);
    }

    @GetMapping("/logout")
    @Operation(description = "用户登出")
    @ControllerLog("用户登出")
    public Result<Boolean> logoutUser(@RequestHeader("Authorization") String token){
        return userService.logoutUser(token);
    }

    @PostMapping("/register")
    @Operation(description = "游客注册")
    @ControllerLog("游客注册")
    public Result<LoginVo> registerUser(@RequestBody RegisterParam registerParam, HttpServletRequest request){
        UserDto userDto = new UserDto();
        userDto.setIpAddress(IpUtils.getIpAddress(request));
        userDto.setIpSource(IpUtils.getIpLocation());
        userDto.setOs(SystemUtil.getOs(request));
        userDto.setBrowser(SystemUtil.getBrowser(request));
        log.info(registerParam.toString());
        return userService.userRegister(registerParam, userDto);
    }

    @PostMapping("/reset")
    @Operation(description = "忘记密码/重置密码")
    @ControllerLog("忘记密码/重置密码")
    public Result<Integer> resetPassword(@RequestBody ResetPasswordParam resetPasswordParam){
        return userService.resetPassword(resetPasswordParam);
    }

}
