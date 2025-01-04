package com.ms.blog.entity.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 用户信息参数
 * @author wyh
 * @date 2023/01/16 10:10
 */
@Data
@Schema(name = "用户信息参数")
public class UserInfoParam {

    @Schema(name = "用户id")
    private Integer id;

    @Schema(name = "用户名")
    @NotBlank
    private String username;

    @Schema(name = "用户昵称")
    private String nickname;

    @Schema(name = "头像链接")
    private String avatar;

    @Schema(name = "介绍")
    private String intro;

    @Schema(name = "网站")
    private String website;
}
