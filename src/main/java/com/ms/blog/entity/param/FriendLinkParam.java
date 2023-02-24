package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 友情链接表单
 * @author wyh
 * @date 2023/02/02 12:01
 */
@Data
@ApiModel("友情链接表单")
public class FriendLinkParam {

    @ApiModelProperty("主键id")
    private Integer id;

    /**
     *  网站名称
     */
    @ApiModelProperty("网站名称")
    private String name;

    /**
     *  网站链接
     */
    @ApiModelProperty("网站链接")
    private String url;

    /**
     * 网站头像地址
     */
    @ApiModelProperty("网站头像地址")
    private String avatar;

    /**
     * 网站信息
     */
    @ApiModelProperty("网站信息")
    private String info;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     *  下架原因
     */
    @ApiModelProperty("下架原因")
    private String reason;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 状态  0下架 1上架
     */
    @ApiModelProperty("状态  0下架 1上架")
    private Integer status;

}
