package com.ms.blog.entity.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 相关链接参数类
 * @author wyh
 * @date 2023/02/07 15:09
 */
@Data
@Schema(name = "相关链接参数类")
public class ModPackRelatedLinkParam {

    @Schema(name = "主键id")
    private Integer id;

    @Schema(name = "整合包id")
    private Integer modPackId;

    @Schema(name = "链接名称")
    private String name;

    @Schema(name = "链接图片")
    private String pic;

    @Schema(name = "链接地址")
    private String url;
}
