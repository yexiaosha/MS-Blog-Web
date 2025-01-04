package com.ms.blog.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 整合包作者视图类
 * @author wyh
 * @date 2023/02/07 14:39
 */
@Data
@Schema(name ="整合包作者视图类")
public class CreatorVo {

    @Schema(name ="作者id")
    private Integer id;

    @Schema(name ="作者用户名")
    private String username;

    @Schema(name ="昵称")
    private String nikeName;
}
