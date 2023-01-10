package com.ms.blog.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 错误日志
 * @author wyh
 * @date 2023/01/09 18:32
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionLog {

    /**
     *  主键id
     */
    private Integer id;

    /**
     *  操作用户
     */
    private String username;

    /**
     * ip
     */
    private String ip;

    /**
     * ip来源
     */
    private String source;

    /**
     * 请求方法
     */
    private String method;

    /**
     *  描述
     */
    private String operation;

    /**
     *  请求参数
     */
    private String params;

    /**
     *  异常对象json格式
     */
    private String exceptionJson;

    /**
     *  异常简单信息
     */
    private String exceptionMessage;

    /**
     * 发生时间
     */
    private Date createTime;
}
