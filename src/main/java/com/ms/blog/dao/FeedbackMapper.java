package com.ms.blog.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.entity.FeedBack;
import com.ms.blog.entity.param.FeedbackSearchParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 反馈接口
 * @author wyh
 * @date 2023/02/13 10:32
 */
@Mapper
public interface FeedbackMapper {

    /**
     * 新增反馈
     * @param feedBack 反馈类
     * @return 结果
     */
    int insertFeedback(FeedBack feedBack);

    /**
     * 根据条件获取反馈
     * @param feedbackSearchParam 反馈参数
     * @param page 分页参数
     * @return 结果
     */
    IPage<FeedBack> getFeedbackByParam(FeedbackSearchParam feedbackSearchParam, Page<FeedBack> page);

    /**
     * 删除反馈
     * @param id 反馈id
     * @return 结果
     */
    int deleteFeedback(@Param("id") Integer id);

    /**
     * 获取所有反馈表
     * @return 列表
     */
    List<FeedBack> getFeedbackList();
}
