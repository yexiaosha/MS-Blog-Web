package com.ms.blog.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.entity.Cancellation;
import com.ms.blog.entity.HandledCancellation;
import com.ms.blog.entity.param.CancellationParam;
import com.ms.blog.entity.param.HandledCancellationParam;
import org.apache.ibatis.annotations.Mapper;

/**
 * 注销表单
 * @author wyh
 * @date 2023/01/13 17:41
 */
@Mapper
public interface CancellationMapper {
    IPage<Cancellation> getCancellationList(CancellationParam cancellationParam, Page<Cancellation> page);
    Cancellation getCancellationById(Integer id);
    int insertHandledCancellation(HandledCancellation handledCancellation);
    IPage<HandledCancellation> getHandledCancellationList(HandledCancellationParam handledCancellationParam, Page<HandledCancellation> page);
}
