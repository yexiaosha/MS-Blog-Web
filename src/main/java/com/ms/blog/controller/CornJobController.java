package com.ms.blog.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务接口
 * @author wyh
 * @date 2023/01/06 14:27
 */
@Deprecated
@RestController
@Api(tags = "定时任务")
@RequestMapping("/msblog/job")
public class CornJobController {

}
