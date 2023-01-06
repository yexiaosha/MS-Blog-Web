package com.ms.blog.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标签接口
 * @author wyh
 * @date 2023/01/06 13:56
 */
@RestController
@Api(tags = "标签接口")
@RequestMapping("/msblog/tags")
public class TagsController {

}
