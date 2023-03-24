package com.ms.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户基本信息视图类
 * @author wyh
 * @date 2023/03/15 09:59
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户基本信息视图类")
public class UserSimpleVo {
    @ApiModelProperty("用户详情id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("头像链接")
    private String avatar;

    @ApiModelProperty("介绍")
    private String intro;

    @ApiModelProperty("网站")
    private String website;
}
