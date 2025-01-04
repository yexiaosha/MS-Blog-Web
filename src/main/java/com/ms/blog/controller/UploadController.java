package com.ms.blog.controller;

import com.ms.blog.common.ErrorCode;
import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ControllerLog;
import com.ms.blog.entity.vo.QiniuVo;
import com.ms.blog.service.UploadService;
import com.ms.blog.util.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 图片资源上传
 * @author gary
 * @date 2023/3/27 16:23
 */

@RestController
@Tag(name = "图片资源上传")
@RequestMapping("/msblog/file")
@CrossOrigin
public class UploadController {

    @Resource
    private UploadService uploadService;

    @PostMapping("/upload")
    @Operation(description = "上传文件")
    @ControllerLog("上传文件")
    public Result uploadFile(@RequestPart MultipartFile multipartFile){
        String result = "失败";
        QiniuVo qiniuVo = new QiniuVo();
        if(!multipartFile.isEmpty()){
            String path = uploadService.uploadQNImg(multipartFile);
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
