package com.ms.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 错误日志视图类
 * @author wyh
 * @date 2023/03/09 15:36
 */

@Data
@ApiModel("错误日志视图类")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionLogVo {
    /**
     *  主键id
     */
    @ApiModelProperty("主键id")
    private Integer id;

    /**
     *  操作用户
     */
    @ApiModelProperty("操作用户")
    private String username;

    /**
     * ip
     */
    @ApiModelProperty("ip")
    private String ip;

    /**
     * ip来源
     */
    @ApiModelProperty("ip来源")
    private String source;

    /**
     * 请求方法
     */
    @ApiModelProperty("请求方法")
    private String method;

    /**
     *  描述
     */
    @ApiModelProperty("描述")
    private String operation;

    /**
     *  请求参数
     */
    @ApiModelProperty("请求参数")
    private String params;

    /**
     *  异常对象json格式

    @ApiModelProperty("异常对象json格式")
    private String exceptionJson;
     */

    /**
     *  异常简单信息
     */
    @ApiModelProperty("异常简单信息")
    private String exceptionMessage;

    /**
     * 发生时间
     */
    @ApiModelProperty("发生时间")
    private Date createTime;
}
