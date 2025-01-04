package com.ms.blog.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name ="整合包相关连接视图类")
public class ModPackRelatedLinkVo {

    @Schema(name = "主键id")
    private Integer id;

    @Schema(name = "链接名字")
    private String name;

    @Schema(name = "链接地址")
    private String url;

    @Schema(name = "链接网站插图")
    private String pic;
}
