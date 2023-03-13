package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 错误日志参数类
 * @author wyh
 * @date 2023/03/09 15:36
 */
@Data
@ApiModel("错误日志参数类")
@EqualsAndHashCode(callSuper = true)
public class ExceptionLogParam extends PageParam {

    /**
     *  操作用户
     */
    @ApiModelProperty("操作用户名")
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
     * 发生时间
     */
    @ApiModelProperty("发生时间")
    private Date createTime;
}
