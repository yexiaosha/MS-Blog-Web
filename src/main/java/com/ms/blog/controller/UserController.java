package com.ms.blog.controller;

import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.common.aspect.annotation.ControllerLog;
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
import com.ms.blog.service.UserService;
import com.ms.blog.util.IpUtils;
import com.ms.blog.util.SystemUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户接口
 * @author wyh
 * @date 2023/01/06 14:03
 */
@RestController
@Api(tags = "用户接口")
@RequestMapping("/msblog/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    @ControllerLog("用户登录")
    public Result<LoginVo> userLogin(@RequestBody LoginParam loginParam){
        return userService.userLogin(loginParam);
    }

    @GetMapping("/info/{username}")
    @ApiOperation("获取用户基础信息")
    @ControllerLog("获取用户基础信息")
    public Result<UserVo> getUserInfo(@PathVariable("username") String username){
        return userService.getUserInfo(username);
    }

    @GetMapping("/info/detail/{username}")
    @ApiOperation("获取用户详情")
    @ControllerLog("获取用户详情")
    public Result<UserAuthVo> getUserInfoDetails(@PathVariable("username") String username){
        return userService.getUserInfoDetails(username);
    }

    @PostMapping("/info/detail/update")
    @ApiOperation("更改用户信息")
    @ControllerLog("更改用户信息")
    public Result updateUserDetailInfo(@RequestBody UserInfoParam userInfoParam){
        return userService.updateUserDetailInfo(userInfoParam);

    }

    @PostMapping("/list")
    @ApiOperation("获取用户列表")
    @ControllerLog("获取用户列表")
    public Result<PageData<UserVo>> getUserList(@RequestBody UserParam userParam){
        return userService.getUserList(userParam);
    }

    @DeleteMapping("/cancellation")
    @ApiOperation("用户注销")
    @ControllerLog("用户注销")
    public Result cancellationUser(@RequestBody CancellationParam cancellationParam, @RequestHeader("Authorization") String token){
        return userService.cancelUserAccount(cancellationParam, token);
    }

    @GetMapping("/logout")
    @ApiOperation("用户登出")
    @ControllerLog("用户登出")
    public Result<Boolean> logoutUser(@RequestHeader("Authorization") String token){
        return userService.logoutUser(token);
    }

    @PostMapping("/register")
    @ApiOperation("游客注册")
    @ControllerLog("游客注册")
    public Result<Integer> registerUser(@RequestBody RegisterParam registerParam, HttpServletRequest request){
        UserDto userDto = new UserDto();
        userDto.setIpAddress(IpUtils.getIpAddress(request));
        userDto.setIpSource(IpUtils.getIpLocation());
        userDto.setOs(SystemUtil.getOs(request));
        userDto.setBrowser(SystemUtil.getBrowser(request));
        return userService.userRegister(registerParam, userDto);
    }

    @PostMapping("/reset")
    @ApiOperation("忘记密码/重置密码")
    @ControllerLog("忘记密码/重置密码")
    private Result resetPassword(@RequestBody ResetPasswordParam resetPasswordParam, @RequestHeader("Authorization") String token){
        return userService.resetPassword(resetPasswordParam, token);
    }

}
