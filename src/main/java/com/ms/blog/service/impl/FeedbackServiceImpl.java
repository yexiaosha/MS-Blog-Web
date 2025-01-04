package com.ms.blog.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ServiceLog;
import com.ms.blog.dao.FeedbackMapper;
import com.ms.blog.entity.FeedBack;
import com.ms.blog.entity.param.FeedbackParam;
import com.ms.blog.entity.param.FeedbackSearchParam;
import com.ms.blog.entity.vo.FeedbackVo;
import com.ms.blog.service.FeedbackService;
import com.ms.blog.util.ResultUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 反馈业务接口实现
 * @author wyh
 * @date 2023/02/13 10:28
 */
@Service
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    private FeedbackMapper feedbackMapper;

    @Override
    @ServiceLog("新增反馈")
    public Result<Integer> insertFeedback(FeedbackParam feedbackParam) {
        FeedBack feedBack = FeedBack.builder()
                .createTime(new Date())
                .content(feedbackParam.getContent())
                .email(feedbackParam.getEmail())
                .title(feedbackParam.getTitle())
                .type(feedbackParam.getType())
                .build();
        return ResultUtils.success(feedbackMapper.insertFeedback(feedBack));
    }

    @Override
    @ServiceLog("查找反馈")
    public Result<PageData<FeedbackVo>> searchFeedback(FeedbackSearchParam feedbackSearchParam) {
        Page<FeedBack> feedbackParamPage = new Page<>(feedbackSearchParam.getCurrentPage(), feedbackSearchParam.getPageSize());
        IPage<FeedBack> feedBackIPage = feedbackMapper.getFeedbackByParam(feedbackSearchParam, feedbackParamPage);
        List<FeedbackVo> feedbackVoList = new ArrayList<>();
        for (FeedBack f : feedBackIPage.getRecords()) {
            FeedbackVo feedbackVo = new FeedbackVo();
            BeanUtils.copyProperties(f, feedbackVo);
            feedbackVoList.add(feedbackVo);
        }

        PageData<FeedbackVo> feedbackVoPageData = new PageData<>(feedbackVoList, feedBackIPage.getTotal(), feedBackIPage.getPages(), feedBackIPage.getCurrent());
        return ResultUtils.success(feedbackVoPageData);
    }

    @Override
    @ServiceLog("删除反馈")
    public Result<Integer> deleteFeedback(Integer id) {
        return ResultUtils.success(feedbackMapper.deleteFeedback(id));
    }

    @Override
    public void outputExcelFile(HttpServletResponse response){
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        try {
            String fileName = URLEncoder.encode("反馈汇总表", "utf-8");
            Date time = new Date();
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName+ time + ".xlsx");
            EasyExcel.write(response.getOutputStream(), FeedBack.class).sheet().doWrite(
                    ()-> feedbackMapper.getFeedbackList()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
