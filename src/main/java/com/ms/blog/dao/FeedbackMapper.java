package com.ms.blog.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.entity.FeedBack;
import com.ms.blog.entity.param.FeedbackSearchParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 反馈接口
 * @author wyh
 * @date 2023/02/13 10:32
 */
@Mapper
public interface FeedbackMapper {
    int insertFeedback(FeedBack feedBack);
    IPage<FeedBack> getFeedbackByParam(FeedbackSearchParam feedbackSearchParam, Page<FeedBack> page);
    int deleteFeedback(Integer id);
    List<FeedBack> getFeedbackList();
}
