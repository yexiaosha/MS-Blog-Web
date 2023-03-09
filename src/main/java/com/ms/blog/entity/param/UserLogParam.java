package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户日志参数类
 * @author wyh
 * @date 2023/03/07 11:07
 */
@Data
@ApiModel("用户日志参数类")
@EqualsAndHashCode(callSuper = true)
public class UserLogParam extends PageParam {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("操作系统")
    private String os;

    @ApiModelProperty("ip地址")
    private String ip;

    @ApiModelProperty("请求类型")
    private String type;

    @ApiModelProperty("操作结果")
    private String result;

    @ApiModelProperty("日志生成时间")
    private Date createTime;

    @ApiModelProperty("浏览器")
    private String browser;

    @ApiModelProperty("客户端类型")
    private String clientType;
}
