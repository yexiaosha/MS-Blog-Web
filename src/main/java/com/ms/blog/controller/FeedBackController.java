package com.ms.blog.controller;

import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.entity.param.FeedbackParam;
import com.ms.blog.entity.param.FeedbackSearchParam;
import com.ms.blog.entity.vo.FeedbackVo;
import com.ms.blog.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;


/**
 * 反馈接口
 * @author wyh
 * @date 2023/01/06 13:38
 */
@RestController
@Tag(name = "反馈接口")
@RequestMapping("/msblog/feedback")
@CrossOrigin
public class FeedBackController {

    @Resource
    private FeedbackService feedbackService;

    @Operation(description = "新增反馈")
    @PostMapping("/insert")
    public Result<Integer> insertFeedback(@RequestBody FeedbackParam feedbackParam){
        return feedbackService.insertFeedback(feedbackParam);
    }

    @Operation(description = "查找反馈")
    @PostMapping("/search")
    public Result<PageData<FeedbackVo>> searchFeedBack(@RequestBody FeedbackSearchParam feedbackSearchParam){
        return feedbackService.searchFeedback(feedbackSearchParam);
    }

    @Operation(description = "删除反馈")
    @DeleteMapping("/delete/{id}")
    public Result<Integer> deleteFeedback(@PathVariable("id") Integer id){
        return feedbackService.deleteFeedback(id);
    }

    @Operation(description = "导出反馈")
    @PostMapping("/output")
    public void outputExcelFile(HttpServletResponse httpServletResponse){
        feedbackService.outputExcelFile(httpServletResponse);
    }
}
