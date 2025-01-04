package com.ms.blog.service;

import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.entity.param.FeedbackParam;
import com.ms.blog.entity.param.FeedbackSearchParam;
import com.ms.blog.entity.vo.FeedbackVo;
import jakarta.servlet.http.HttpServletResponse;


/**
 * 用户反馈业务接口
 * @author wyh
 * @date 2023/02/13 10:26
 */
public interface FeedbackService {
    Result<Integer> insertFeedback(FeedbackParam feedbackParam);
    Result<PageData<FeedbackVo>> searchFeedback(FeedbackSearchParam feedbackSearchParam);
    Result<Integer> deleteFeedback(Integer id);
    void outputExcelFile(HttpServletResponse httpServletResponse);
}
