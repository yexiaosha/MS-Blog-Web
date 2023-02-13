package com.ms.blog.controller;

import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.entity.param.FeedbackParam;
import com.ms.blog.entity.param.FeedbackSearchParam;
import com.ms.blog.entity.vo.FeedbackVo;
import com.ms.blog.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 反馈接口
 * @author wyh
 * @date 2023/01/06 13:38
 */
@RestController
@Api(tags = "反馈接口")
@RequestMapping("/msblog/feedback")
public class FeedBackController {

    @Resource
    private FeedbackService feedbackService;

    @ApiOperation("新增反馈")
    @PostMapping("/insert")
    public Result<Integer> insertFeedback(@RequestBody FeedbackParam feedbackParam){
        return feedbackService.insertFeedback(feedbackParam);
    }

    @ApiOperation("查找反馈")
    @PostMapping("/search")
    public Result<PageData<FeedbackVo>> searchFeedBack(@RequestBody FeedbackSearchParam feedbackSearchParam){
        return feedbackService.searchFeedback(feedbackSearchParam);
    }

    @ApiOperation("删除反馈")
    @DeleteMapping("/delete/{id}")
    public Result<Integer> deleteFeedback(@PathVariable("id") Integer id){
        return feedbackService.deleteFeedback(id);
    }

    @ApiOperation("导出反馈")
    @PostMapping("/output")
    public void outputExcelFile(HttpServletResponse httpServletResponse){
        feedbackService.outputExcelFile(httpServletResponse);
    }
}
