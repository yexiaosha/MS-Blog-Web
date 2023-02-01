package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查找用户表单
 * @author wyh
 * @date 2023/01/13 11:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("查找用户表单")
public class UserParam extends PageParam {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("ip位置")
    private String ipSource;

    @ApiModelProperty("权限id")
    private Integer roleId;

    @ApiModelProperty("操作系统")
    private String os;

    @ApiModelProperty("浏览器")
    private String browser;

}
