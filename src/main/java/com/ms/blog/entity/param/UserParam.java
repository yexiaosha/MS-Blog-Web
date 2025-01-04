package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查找用户表单
 * @author wyh
 * @date 2023/01/13 11:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "查找用户表单")
public class UserParam extends PageParam {

    @Schema(name = "用户名")
    private String username;

    @Schema(name = "ip位置")
    private String ipSource;

    @Schema(name = "权限id")
    private Integer roleId;

    @Schema(name = "操作系统")
    private String os;

    @Schema(name = "浏览器")
    private String browser;

}
