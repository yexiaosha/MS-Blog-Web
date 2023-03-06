package com.ms.blog.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.entity.Cancellation;
import com.ms.blog.entity.HandledCancellation;
import com.ms.blog.entity.param.CancellationParam;
import com.ms.blog.entity.param.HandledCancellationParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 注销表单
 * @author wyh
 * @date 2023/01/13 17:41
 */
@Mapper
public interface CancellationMapper {

    /**
     * 获取注销请求
     * @param cancellationParam 注销表单参数
     * @param page  分页对象
     * @return
     */
    IPage<Cancellation> getCancellationList(CancellationParam cancellationParam, Page<Cancellation> page);

    /**
     * 通过注销请求id获取对象
     * @param id  id
     * @return 注销请求
     */
    Cancellation getCancellationById(@Param("id") Integer id);

    /**
     * 新增已经注销的请求
     * @param handledCancellation   已处理的注销请求
     * @return  结果
     */
    int insertHandledCancellation(HandledCancellation handledCancellation);

    /**
     * 获取已经处理的
     * @param handledCancellationParam 已处理注销请求
     * @param page  分页参数
     * @return  分页结果
     */
    IPage<HandledCancellation> getHandledCancellationList(HandledCancellationParam handledCancellationParam, Page<HandledCancellation> page);
}
