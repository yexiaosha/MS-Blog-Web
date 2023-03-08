package com.ms.blog.handler;

import com.ms.blog.common.ErrorCode;
import com.ms.blog.common.Result;
import com.ms.blog.util.ResultUtils;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 校验器错误处理
 * @author wyh
 * @date 2023/02/01 10:00
 */
@RestControllerAdvice
@Order(0)
@Slf4j
public class ValidateExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Result<Boolean> validateErrorHandler(BindException e) {
        ObjectError error = e.getAllErrors().get(0);
        log.error("数据验证异常：{}", error.getDefaultMessage());
        return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Boolean> validateErrorHandler(MethodArgumentNotValidException e) {
        ObjectError error = e.getAllErrors().get(0);
        log.error("数据验证错误：{}", error.getDefaultMessage());
        return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Boolean> validateErrorHandler(ConstraintViolationException e) {
        log.error("数据验证异常：", e);
        return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
    }

}