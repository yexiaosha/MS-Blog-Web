package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 整合包基础信息表单
 *
 * @author wyh
 * @date 2023/02/05 15:49
 */
@Data
@ApiModel("整合包基础信息表单")
public class ModPackParam {

    @ApiModelProperty("整合包名称")
    private String name;

    @ApiModelProperty("整合包状态")
    private Integer status;

    @ApiModelProperty("开发进度")
    private Integer overallProgress;

    @ApiModelProperty("介绍")
    private String introduce;

    @ApiModelProperty("版本")
    private String version;

    private Date updateDate;
}
