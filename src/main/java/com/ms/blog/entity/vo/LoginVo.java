package com.ms.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登陆返回视图类
 * @author wyh
 * @date 2023/01/11 17:02
 */
@Data
@ApiModel("登录返回视图类")
public class LoginVo {

    @ApiModelProperty("生成token")
    private String token;

    @ApiModelProperty("用户类型")
    private String role;

    @ApiModelProperty("用户简单信息")
    private UserSimpleVo userSimpleVo;
}
