package com.ms.blog.controller;

import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ControllerLog;
import com.ms.blog.entity.param.ModPackParam;
import com.ms.blog.entity.vo.ModPackVo;
import com.ms.blog.service.ModPackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 整合包接口
 * @author wyh
 * @date 2023/01/12 17:06
 */
@RestController
@RequestMapping("/msblog/modpack")
@Api(tags = "整合包接口")
public class ModPackController {

    @Resource
    private ModPackService modPackService;

    @PostMapping("/insert")
    @ApiOperation("新增整合包")
    @Deprecated
    public Result<?> insertModPack(@RequestBody ModPackParam modPackParam){
        return null;
    }

    @PostMapping("/update")
    @ApiOperation("更改整合包信息")
    @ControllerLog("更新整合包信息")
    public Result<Integer> updateModPack(){
        return null;
    }

    @GetMapping("/list")
    @ApiOperation("查看整合包列表")
    @ControllerLog("查看整合包列表")
    public Result<List<ModPackVo>> listModPacks(){
        return modPackService.getModPackList();
    }

    @PostMapping("/delete")
    @ApiOperation("更改整合包状态 0正式上线 1测试中 2停更")
    @ControllerLog("更改整合包状态")
    @Deprecated
    public Result<?> updateModPacksStatus(){
        return null;
    }

    @GetMapping("/{id}")
    @ApiOperation("通过整合包id获取整合包详情")
    @ControllerLog("通过整合包id获取整合包详情")
    public Result<ModPackVo> getModPackById(@PathVariable("id") Integer id){
        return modPackService.getModPackById(id);
    }
}
