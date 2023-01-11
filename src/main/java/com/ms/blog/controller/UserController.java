package com.ms.blog.controller;

import com.ms.blog.common.Result;
import com.ms.blog.common.aspect.annotation.ControllerLog;
import com.ms.blog.entity.dto.UserDto;
import com.ms.blog.entity.param.LoginParam;
import com.ms.blog.entity.param.RegisterParam;
import com.ms.blog.entity.vo.LoginVo;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    public Result userInfo(){
        return null;
    }

    @DeleteMapping("/cancellation")
    @ApiOperation("用户注销")
    public Result cancellationUser(){
        return null;
    }

    @GetMapping("/logout")
    @ApiOperation("用户登出")
    public Result logoutUser(){
        return null;
    }

    @PostMapping("/register")
    @ApiOperation("游客注册")
    public Result registerUser(@RequestBody RegisterParam registerParam, HttpServletRequest request){
        UserDto userDto = new UserDto();
        userDto.setIpAddress(IpUtils.getIpAddress(request));
        userDto.setIpSource(IpUtils.getIpLocation());
        userDto.setOs(SystemUtil.getOs(request));
        userDto.setBrowser(SystemUtil.getBrowser(request));
        return userService.userRegister(registerParam, userDto);
    }

    @PostMapping("/reset")
    @ApiOperation("忘记密码/重置密码")
    private Result resetPassword(){
        return null;
    }

}
