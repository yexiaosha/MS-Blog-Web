package com.ms.blog.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户日志
 * @author wyh
 * @date 2023/01/09 18:31
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLog {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 操作用户名
     */
    private String username;

    /**
     *  ip地址
     */
    private String ip;

    /**
     * 操作地址
     */
    private String address;

    /**
     *  操作类型
     */
    private String type;

    /**
     *  操作日志
     */
    private String description;

    /**
     * 操作模块
     */
    private String model;

    /**
     * 操作时间
     */
    private Date createTime;

    /**
     *  操作结果
     */
    private String result;

    /**
     * 操作系统
     */
    private String accessOs;

    /**
     *  浏览器
     */
    private String browser;

    /**
     * 客户端类型
     */
    private String clientType;
}
