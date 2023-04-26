package com.ms.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户日志视图类
 * @author wyh
 * @date 2023/03/07 11:07
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户日志视图类")
public class UserLogVo {
    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Integer id;

    /**
     * 操作用户名
     */
    @ApiModelProperty("操作用户名")
    private String username;

    /**
     *  ip地址
     */
    @ApiModelProperty("ip地址")
    private String ip;

    /**
     * 操作地址
     */
    @ApiModelProperty("操作地址")
    private String address;

    /**
     *  操作类型
     */
    @ApiModelProperty("操作类型")
    private String type;

    /**
     *  操作日志
     */
    @ApiModelProperty("操作日志")
    private String description;

    /**
     * 操作模块
     */
    @ApiModelProperty("操作模块")
    private String model;

    /**
     * 操作时间
     */
    @ApiModelProperty("操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     *  操作结果
     */
    @ApiModelProperty("操作结果")
    private String result;

    /**
     * 操作系统
     */
    @ApiModelProperty("操作系统")
    private String accessOs;

    /**
     *  浏览器
     */
    @ApiModelProperty("浏览器")
    private String browser;

    /**
     * 客户端类型
     */
    @ApiModelProperty("客户端类型")
    private String clientType;

}
