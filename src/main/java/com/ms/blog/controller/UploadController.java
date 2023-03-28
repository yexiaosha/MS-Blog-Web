package com.ms.blog.controller;

import com.ms.blog.common.ErrorCode;
import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ControllerLog;
import com.ms.blog.entity.vo.QiniuVo;
import com.ms.blog.service.UploadService;
import com.ms.blog.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 图片资源上传
 * @author gary
 * @date 2023/3/27 16:23
 */

@RestController
@Api(tags = "图片资源上传")
@RequestMapping("/msblog/file")
public class UploadController {

    @Resource
    private UploadService uploadService;

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    @ControllerLog("上传文件")
    public Result uploadFile(@RequestPart MultipartFile file){
        String result = "失败";
        QiniuVo qiniuVo = new QiniuVo();
        if(!file.isEmpty()){
            String path = uploadService.uploadQNImg(file);
            if(path.equals(result)){
                return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), "上传失败");
            }else{
                System.out.println("七牛云返回的图片链接是：" + path);
                qiniuVo.setPath(path);
                return ResultUtils.success(qiniuVo);
            }
        }
        return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), "上传失败");

    }
}
