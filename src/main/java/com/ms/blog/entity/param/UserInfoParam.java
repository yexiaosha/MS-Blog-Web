package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 用户信息参数
 * @author wyh
 * @date 2023/01/16 10:10
 */
@Data
@ApiModel("用户信息参数")
public class UserInfoParam {

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("用户名")
    @NotBlank
    private String username;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("头像链接")
    private String avatar;

    @ApiModelProperty("介绍")
    private String intro;

    @ApiModelProperty("网站")
    private String website;
}
