package com.ms.blog.controller;

import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.common.aspect.annotation.ControllerLog;
import com.ms.blog.entity.param.CancellationParam;
import com.ms.blog.entity.param.HandleCancellationParam;
import com.ms.blog.entity.param.HandledCancellationParam;
import com.ms.blog.entity.vo.CancellationVo;
import com.ms.blog.entity.vo.HandledCancellationVo;
import com.ms.blog.service.CancellationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注销接口
 * @author wyh
 * @date 2023/01/13 17:28
 */
@RestController
@RequestMapping("msblog/cancel")
@Api("注销接口")
public class CancellationController {

    @Resource
    private CancellationService cancellationService;

    @PostMapping("/list/request")
    @ApiOperation("获取注销请求列表")
    @ControllerLog("获取注销请求列表")
    public Result<PageData<CancellationVo>> getCancellationList(@RequestBody CancellationParam cancellationParam){
        return cancellationService.getCancellationList(cancellationParam);
    }

    @PostMapping("/handle")
    @ApiOperation("处理注销请求")
    @ControllerLog("处理注销请求")
    public Result<Integer> handleCancellation(@RequestBody HandleCancellationParam handleCancellationParam, HttpServletRequest request){
        String handler = (String) request.getSession().getAttribute("username");
        return cancellationService.handleCancellation(handleCancellationParam, handler);
    }

    @PostMapping("/list/handled")
    @ApiOperation("获取已处理注销请求列表")
    public Result<PageData<HandledCancellationVo>> getHandledCancellationList(@RequestBody HandledCancellationParam handledCancellationParam){
        return cancellationService.getHandledCancellationList(handledCancellationParam);

    }
}
