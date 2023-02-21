package com.ms.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.common.ErrorCode;
import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ServiceLog;
import com.ms.blog.dao.CancellationMapper;
import com.ms.blog.entity.Cancellation;
import com.ms.blog.entity.HandledCancellation;
import com.ms.blog.entity.param.CancellationParam;
import com.ms.blog.entity.param.HandleCancellationParam;
import com.ms.blog.entity.param.HandledCancellationParam;
import com.ms.blog.entity.vo.CancellationVo;
import com.ms.blog.entity.vo.HandledCancellationVo;
import com.ms.blog.service.CancellationService;
import com.ms.blog.service.UserService;
import com.ms.blog.util.ResultUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 注销请求业务接口实现
 * @author wyh
 * @date 2023/01/13 17:39
 */
@Service
public class CancellationServiceImpl implements CancellationService {

    @Resource
    private CancellationMapper cancellationMapper;

    @Resource
    private UserService userService;

    @Override
    @ServiceLog("获取注销请求列表")
    public Result<PageData<CancellationVo>> getCancellationList(CancellationParam cancellationParam) {
        Page<Cancellation> page = new Page<>(cancellationParam.getCurrentPage(), cancellationParam.getPageSize());
        IPage<Cancellation> iPage = cancellationMapper.getCancellationList(cancellationParam, page);
        List<CancellationVo> cancellationVoList = new ArrayList<>();
        for (Cancellation cancellation : page.getRecords()) {
            cancellationVoList.add(copy(cancellation));
        }
        return ResultUtils.success(new PageData<>(cancellationVoList, iPage.getTotal(), iPage.getPages(), iPage.getCurrent()));
    }

    @Override
    @ServiceLog("处理注销请求")
    @Transactional(rollbackFor = Exception.class)
    public Result<Integer> handleCancellation(HandleCancellationParam handleCancellationParam,String handler) {
        Cancellation cancellation = cancellationMapper.getCancellationById(handleCancellationParam.getCancellationId());
        if (cancellation == null){
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        HandledCancellation handledCancellation = HandledCancellation.builder()
                .cancellationId(cancellation.getId())
                .handleDate(new Date())
                .handlerName(handler)
                .handlerId(userService.getUserInfo(handler).getData().getId())
                .cancellationCreateTime(cancellationMapper.getCancellationById(handleCancellationParam.getCancellationId()).getCreateTime())
                .result(handleCancellationParam.getResult())
                .resultSuggest(handleCancellationParam.getResultSuggest())
                .userId(cancellationMapper.getCancellationById(handleCancellationParam.getCancellationId()).getUserId())
                .username(cancellationMapper.getCancellationById(handleCancellationParam.getCancellationId()).getUsername())
                .build();

        return ResultUtils.success(cancellationMapper.insertHandledCancellation(handledCancellation));
    }

    @Override
    @ServiceLog("获取已处理列表")
    public Result<PageData<HandledCancellationVo>> getHandledCancellationList(
            HandledCancellationParam handledCancellationParam) {
        Page<HandledCancellation> page = new Page<>(handledCancellationParam.getCurrentPage(), handledCancellationParam.getPageSize());
        IPage<HandledCancellation> iPage = cancellationMapper.getHandledCancellationList(handledCancellationParam, page);
        List<HandledCancellationVo> handledCancellationVoList = new ArrayList<>();
        for (HandledCancellation h : iPage.getRecords()) {
            handledCancellationVoList.add(copy(h));
        }
        PageData<HandledCancellationVo> pageData = new PageData<>(handledCancellationVoList, iPage.getTotal(), iPage.getPages(), iPage.getCurrent());
        return ResultUtils.success(pageData);
    }

    public HandledCancellationVo copy(HandledCancellation handledCancellation){
        return HandledCancellationVo.builder()
                .cancellationCreateTime(handledCancellation.getCancellationCreateTime())
                .cancellationId(handledCancellation.getCancellationId())
                .handleDate(handledCancellation.getHandleDate())
                .id(handledCancellation.getId())
                .handler(handledCancellation.getHandlerName())
                .handlerId(handledCancellation.getHandlerId())
                .result(handledCancellation.getResult())
                .resultSuggest(handledCancellation.getResultSuggest())
                .userId(handledCancellation.getUserId())
                .build();

    }

    public CancellationVo copy(Cancellation cancellation){
        return CancellationVo.builder()
                .id(cancellation.getId())
                .createTime(cancellation.getCreateTime())
                .email(cancellation.getEmail())
                .reasonText(cancellation.getReasonText())
                .userId(cancellation.getUserId())
                .username(cancellation.getUsername())
                .build();

    }
}
