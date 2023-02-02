package com.ms.blog.util;

import com.ms.blog.common.Result;

/**
 * 通用返回结果组件
 * @author wyh
 * @date 2023/01/06 16:35
 */
public class ResultUtils {
    public static <T> Result<T> success(T data) {
        return new Result<>(1, "00000", "success", data);
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> fail(String code, String msg) {
        return new Result<>(0, code, msg, null);
    }

    public static <T> Result<T> success(String msg){
        return new Result<>(1, "00000", msg, null);
    }

    public static <T> Result<T> success(String msg, T data){
        return new Result<>(1, "00000", msg, data);
    }

}
