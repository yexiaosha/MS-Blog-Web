package com.ms.blog.handler;

import com.ms.blog.common.Result;
import com.ms.blog.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一接口异常处理
 * 对@Controller进行异常处理
 * @author wyh
 * @date 2022/11/18 17:35
 */

@RestControllerAdvice
@Slf4j
public class AllExceptionHandler {

    /**
     * 通用异常处理
     * @param ex 异常类型
     * @return 异常返回类型
     */
    @ExceptionHandler(Exception.class)
    public Result<Boolean> doException(Exception ex) {
        log.error("",ex);
        return ResultUtils.fail("-9999", "系统异常");
    }
}
