package com.ms.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户视图类
 * @author wyh
 * @date 2023/01/13 09:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name ="用户视图类")
public class UserVo {
    /**
     * 主键id
     */
    @Schema(name ="主键id")
    private Integer id;

    /**
     * 账号
     */
    @Schema(name ="账号")
    private String username;

    /**
     * 登陆密码
     */
    @Schema(name ="登陆密码")
    private String password;

    /**
     * 创建时间
     */
    @Schema(name ="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(name ="更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh", timezone = "GMT+8")
    private Date updateTime;

    /**
     *  状态 0正常 1注销中 2冻结中
     */
    @Schema(name ="状态 0正常 1注销中 2冻结中")
    private Integer status;

    /**
     * 登陆方式 0账号密码 1qq 2wechat
     */
    @Schema(name ="登陆方式 0账号密码 1qq 2wechat")
    private Integer loginType;

    /**
     *  用户详情id
     */
    @Schema(name ="用户详情id")
    private Integer userAuthId;

    /**
     * 角色id
     */
    @Schema(name ="角色id")
    private Integer roleId;

    /**
     * IP地址
     */
    @Schema(name ="IP地址")
    private String ipAddress;

    /**
     *  IP来源
     */
    @Schema(name ="IP来源")
    private String ipSource;

    /**
     * 操作系统
     */
    @Schema(name ="操作系统")
    private String os;

    /**
     * 最后登录时间
     */
    @Schema(name ="最后登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lastLoginTime;

    /**
     * 浏览器
     */
    @Schema(name ="浏览器")
    private String browser;
}
