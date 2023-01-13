package com.ms.blog.controller;

import com.ms.blog.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 整合包接口
 * @author wyh
 * @date 2023/01/12 17:06
 */
@RestController
@RequestMapping("/msblog/modpack")
@Api("整合包接口")
public class ModPackController {

    @PostMapping("/insert")
    @ApiOperation("新增整合包")
    public Result insertModPack(){
        return null;
    }

    @PostMapping("/update")
    @ApiOperation("更改整合包信息")
    public Result updateModPack(){
        return null;
    }

    @GetMapping("/list")
    @ApiOperation("查看整合包")
    public Result listModPacks(){
        return null;
    }

    @PostMapping("/delete")
    @ApiOperation("更改整合包状态 0正式上线 1测试中 2停更")
    public Result updateModPacksStatus(){
        return null;
    }
}
