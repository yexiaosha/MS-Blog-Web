package com.ms.blog.controller;

import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.common.UserThreadLocal;
import com.ms.blog.common.annotation.ControllerLog;
import com.ms.blog.entity.param.CancellationParam;
import com.ms.blog.entity.param.HandleCancellationParam;
import com.ms.blog.entity.param.HandledCancellationParam;
import com.ms.blog.entity.vo.CancellationVo;
import com.ms.blog.entity.vo.HandledCancellationVo;
import com.ms.blog.service.CancellationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
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
@Tag(name = "注销接口")
public class CancellationController {

    @Resource
    private CancellationService cancellationService;

    @PostMapping("/list/request")
    @Operation(description = "获取注销请求列表")
    @ControllerLog("获取注销请求列表")
    public Result<PageData<CancellationVo>> getCancellationList(@RequestBody CancellationParam cancellationParam){
        return cancellationService.getCancellationList(cancellationParam);
    }

    @PostMapping("/handle")
    @Operation(description = "处理注销请求")
    @ControllerLog("处理注销请求")
    public Result<Integer> handleCancellation(@RequestBody HandleCancellationParam handleCancellationParam){
        String handlerName = UserThreadLocal.get().getUsername();
        return cancellationService.handleCancellation(handleCancellationParam, handlerName);
    }

    @PostMapping("/list/handled")
    @Operation(description = "获取已处理注销请求列表")
    public Result<PageData<HandledCancellationVo>> getHandledCancellationList(@RequestBody HandledCancellationParam handledCancellationParam){
        return cancellationService.getHandledCancellationList(handledCancellationParam);

    }
}
