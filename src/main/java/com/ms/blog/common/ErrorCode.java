package com.ms.blog.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码定义
 * @author wyh
 * @data 2022/11/18 14:32
 */

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /**
     * 通用错误枚举
     */
    PARAMS_ERROR("A0410", "请求参数错误"),
    ACCOUNT_PWD_NOT_EXIST("A0201", "用户账户账号不存在"),
    ACCOUNT_HAS_EXIST("A0111", "用户账户已经存在"),
    ACCOUNT_PWD_ERROR("A0210", "账户账号或密码错误"),
    ACCOUNT_NOT_AUTHORIZATION("A0301", "用户未授权"),
    CAPTCHA_ERROR("A0130", "验证码发生错误，请重试"),
    ACCOUNT_HAS_DISABLED("A0322", "该用户已被冻结"),
    EMAIL_SEND_ERROR("A0135","邮件发送错误"),
    EMAIL_VERIFY_FAIL("A0132", "邮箱校验错误"),
    ARTICLE_NOT_EXIST("A0021","文章不存在");

    /**
     * 反序列化
     */
    @JsonValue
    private String code;
    private String msg;
    private static final Map<String, ErrorCode> VALUES = new HashMap<>();

    static {
        for (ErrorCode enumObject : ErrorCode.values()) {
            ErrorCode.VALUES.put(enumObject.getCode(), enumObject);
        }
    }

    @JsonCreator
    public static ErrorCode idValueOf(String code) {
        return ErrorCode.VALUES.get(code);
    }


}
