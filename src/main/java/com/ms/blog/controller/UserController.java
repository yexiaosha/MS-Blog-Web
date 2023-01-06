package com.ms.blog.controller;

import com.ms.blog.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class UserController {

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result userLogin(){
        return null;
    }

    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    public Result userInfo(){
        return null;
    }

}
