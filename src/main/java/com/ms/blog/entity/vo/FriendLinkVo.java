package com.ms.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 友情链接视图类
 * @author wyh
 * @date 2023/02/02 11:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("友情链接视图类")
public class FriendLinkVo {
    /**
     * 主键id
     */
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
     * 状态  0待审核 1通过
     */
    @ApiModelProperty("状态  0待审核 1通过")
    private Integer status;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date creatTime;

    /**
     *  更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
