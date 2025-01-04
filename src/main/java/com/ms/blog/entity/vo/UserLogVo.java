package com.ms.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name ="用户日志视图类")
public class UserLogVo {
    /**
     * 主键id
     */
    @Schema(name ="主键id")
    private Integer id;

    /**
     * 操作用户名
     */
    @Schema(name ="操作用户名")
    private String username;

    /**
     *  ip地址
     */
    @Schema(name ="ip地址")
    private String ip;

    /**
     * 操作地址
     */
    @Schema(name ="操作地址")
    private String address;

    /**
     *  操作类型
     */
    @Schema(name ="操作类型")
    private String type;

    /**
     *  操作日志
     */
    @Schema(name ="操作日志")
    private String description;

    /**
     * 操作模块
     */
    @Schema(name ="操作模块")
    private String model;

    /**
     * 操作时间
     */
    @Schema(name ="操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     *  操作结果
     */
    @Schema(name ="操作结果")
    private String result;

    /**
     * 操作系统
     */
    @Schema(name ="操作系统")
    private String accessOs;

    /**
     *  浏览器
     */
    @Schema(name ="浏览器")
    private String browser;

    /**
     * 客户端类型
     */
    @Schema(name ="客户端类型")
    private String clientType;

}
