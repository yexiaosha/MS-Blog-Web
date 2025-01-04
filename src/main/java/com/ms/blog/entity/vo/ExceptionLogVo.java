package com.ms.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 错误日志视图类
 * @author wyh
 * @date 2023/03/09 15:36
 */

@Data
@Schema(name ="错误日志视图类")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionLogVo {
    /**
     *  主键id
     */
    @Schema(name ="主键id")
    private Integer id;

    /**
     *  操作用户
     */
    @Schema(name ="操作用户")
    private String username;

    /**
     * ip
     */
    @Schema(name ="ip")
    private String ip;

    /**
     * ip来源
     */
    @Schema(name ="ip来源")
    private String source;

    /**
     * 请求方法
     */
    @Schema(name ="请求方法")
    private String method;

    /**
     *  描述
     */
    @Schema(name ="描述")
    private String operation;

    /**
     *  请求参数
     */
    @Schema(name ="请求参数")
    private String params;

    /**
     *  异常对象json格式

    @ApiModelProperty("异常对象json格式")
    private String exceptionJson;
     */

    /**
     *  异常简单信息
     */
    @Schema(name ="异常简单信息")
    private String exceptionMessage;

    /**
     * 发生时间
     */
    @Schema(name ="发生时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
