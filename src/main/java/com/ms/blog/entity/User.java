package com.ms.blog.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户类
 * @author wyh
 * @date 2023/01/09 18:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 登陆密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     *  状态 0正常 1注销中 2冻结中
     */
    private Integer status;

    /**
     * 登陆方式 0账号密码 1qq 2wechat
     */
    private Integer loginType;

    /**
     *  用户详情id
     */
    private Long userAuthId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * IP地址
     */
    private String ipAddress;

    /**
     *  IP来源
     */
    private String ipSource;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 浏览器
     */
    private String browser;
}
