package com.ms.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 * 用户详细信息视图类
 * @author wyh
 * @date 2023/01/13 09:55
 */
@Data
@Builder
@ApiModel("用户详细信息视图类")
public class UserAuthVo {
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Integer id;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nikeName;

    /**
     * 头像链接
     */
    @ApiModelProperty("头像链接")
    private String avatar;

    /**
     *  用户简介
     */
    @ApiModelProperty("用户简介")
    private String intro;

    /**
     *  个人网站
     */
    @ApiModelProperty("个人网站")
    private String website;

    /**
     * 是否禁用
     */
    @ApiModelProperty("是否禁用")
    private Integer isDisabled;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
