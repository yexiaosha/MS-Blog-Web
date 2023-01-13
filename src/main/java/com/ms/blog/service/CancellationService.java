package com.ms.blog.service;

import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.entity.param.CancellationParam;
import com.ms.blog.entity.param.HandleCancellationParam;
import com.ms.blog.entity.param.HandledCancellationParam;
import com.ms.blog.entity.vo.CancellationVo;
import com.ms.blog.entity.vo.HandledCancellationVo;

/**
 * 注销记录服务
 * @author wyh
 * @date 2023/01/13 17:37
 */
public interface CancellationService {

    /**
     * 获取注销请求列表
     * @param cancellationParam 注销请求参数
     * @return 请求列表
     */
    Result<PageData<CancellationVo>> getCancellationList(CancellationParam cancellationParam);

    Result<Integer> handleCancellation(HandleCancellationParam handleCancellationParam, String handler);

    Result<PageData<HandledCancellationVo>> getHandledCancellationList(HandledCancellationParam handledCancellationParam);
}
