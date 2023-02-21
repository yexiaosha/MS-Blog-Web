package com.ms.blog.controller;

import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ControllerLog;
import com.ms.blog.entity.param.ModPackRelatedLinkParam;
import com.ms.blog.service.ModPackRelatedLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 整合包相关链接接口
 * @author wyh
 * @date 2023/02/07 15:10
 */
@RestController
@RequestMapping("/msblog/relink")
@Api(tags = "整合包相关链接接口")
public class ModPackRelatedLinkController {

    @Resource
    private ModPackRelatedLinkService modPackRelatedLinkService;

    @GetMapping("/list")
    @ControllerLog("插入整合包相关链接")
    @ApiOperation("插入整合包相关链接")
    public Result<Integer> insertRelatedLink(@RequestBody ModPackRelatedLinkParam modPackRelatedLinkParam){
        return modPackRelatedLinkService.insertRelatedLink(modPackRelatedLinkParam);
    }

    @DeleteMapping("/delete/{id}")
    @ControllerLog("删除某个整合包链接")
    @ApiOperation("删除某个整合包链接")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "相关链接id")
    )
    public Result<Integer> deleteRelatedLink(@PathVariable("id") Integer id){
        return modPackRelatedLinkService.deleteRelatedLink(id);
    }

    @PutMapping("/update")
    @ControllerLog("更改相关链接信息")
    @ApiOperation("更改相关链接信息")
    public Result<Integer> updateRelatedLink(@RequestBody ModPackRelatedLinkParam modPackRelatedLinkParam){
        return modPackRelatedLinkService.updateRelatedLink(modPackRelatedLinkParam);

    }
}
