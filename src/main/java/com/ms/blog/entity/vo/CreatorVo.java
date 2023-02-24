package com.ms.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 整合包作者视图类
 * @author wyh
 * @date 2023/02/07 14:39
 */
@Data
@ApiModel("整合包作者视图类")
public class CreatorVo {

    @ApiModelProperty("作者id")
    private Integer id;

    @ApiModelProperty("作者用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nikeName;
}
