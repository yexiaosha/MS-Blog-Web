package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 相关链接参数类
 * @author wyh
 * @date 2023/02/07 15:09
 */
@Data
@ApiModel("相关链接参数类")
public class ModPackRelatedLinkParam {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("整合包id")
    private Integer modPackId;

    @ApiModelProperty("链接名称")
    private String name;

    @ApiModelProperty("链接图片")
    private String pic;

    @ApiModelProperty("链接地址")
    private String url;
}
