package com.ms.blog.controller;

import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ControllerLog;
import com.ms.blog.entity.param.FriendLinkParam;
import com.ms.blog.entity.param.FriendLinkSearchParam;
import com.ms.blog.entity.vo.FriendLinkVo;
import com.ms.blog.service.LinksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 友情链接接口
 * @author wyh
 * @date 2023/01/05 18:10
 */
@RestController
@Tag(name = "友情链接接口")
@RequestMapping("/msblog/links")
@CrossOrigin
public class LinksController {

    @Resource
    private LinksService linksService;

    @PostMapping("/list")
    @Operation(description = "获取友情链接")
    @ControllerLog("获取友情链接")
    public Result<PageData<FriendLinkVo>> getFriendLinks(@RequestBody FriendLinkSearchParam friendLinkSearchParam){
        return linksService.getFriendLinks(friendLinkSearchParam);
    }

    @PostMapping("/insert")
    @Operation(description = "新增友情链接")
    @ControllerLog("新增友情链接")
    public Result<Integer> insertFriendLink(@RequestBody FriendLinkParam friendLinkParam){
        return linksService.insertFriendLink(friendLinkParam);
    }

    @DeleteMapping("/delete")
    @Operation(description = "更改友情链接信息")
    @ControllerLog("更改友情链接信息")
    public Result<Integer> updateFriendLink (@RequestBody FriendLinkParam friendLinkParam){
        return linksService.updateFriendLink(friendLinkParam);
    }

    @PostMapping("/input")
    @Operation(description = "通过表格批量导入友情链接")
    public void inputLinks(HttpServletResponse response, @RequestPart("file") MultipartFile multipartFile) throws IOException {
        linksService.uploadFriendLinkByExcel(response, multipartFile);
    }

    @GetMapping("/download")
    @Operation(description = "下载上传表格模板")
    @ControllerLog("下载上传表格模板")
    public void downloadTemplate(HttpServletResponse response){
        linksService.downloadTemplate(response);
    }
}
