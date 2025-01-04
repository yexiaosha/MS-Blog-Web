package com.ms.blog.entity.param;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 整合包基础信息表单
 *
 * @author wyh
 * @date 2023/02/05 15:49
 */
@Data
@Schema(name ="整合包基础信息表单")
public class ModPackParam {

    @Schema(name ="主键id")
    private Integer id;

    @Schema(name ="整合包名称")
    private String name;

    @Schema(name ="整合包状态")
    private Integer status;

    @Schema(name ="开发进度")
    private Integer overallProgress;

    @Schema(name ="介绍")
    private String introduce;

    @Schema(name ="版本")
    private String version;

    @Schema(name ="更新时间")
    private Date updateDate;
}
