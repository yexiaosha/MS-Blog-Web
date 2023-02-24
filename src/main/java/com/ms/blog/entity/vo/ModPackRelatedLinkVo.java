package com.ms.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 整合包相关连接视图类
 *
 * @author wyh
 * @date 2023/02/05 15:59
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("整合包相关连接视图类")
public class ModPackRelatedLinkVo {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("链接名字")
    private String name;

    @ApiModelProperty("链接地址")
    private String url;

    @ApiModelProperty("链接网站插图")
    private String pic;
}
