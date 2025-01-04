package com.ms.blog.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户基本信息视图类
 * @author wyh
 * @date 2023/03/15 09:59
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "用户基本信息视图类")
public class UserSimpleVo {
    @Schema(name = "用户详情id")
    private Integer id;

    @Schema(name = "用户名")
    private String username;

    @Schema(name = "昵称")
    private String nickname;

    @Schema(name = "邮箱")
    private String email;

    @Schema(name = "头像链接")
    private String avatar;

    @Schema(name = "介绍")
    private String intro;

    @Schema(name = "网站")
    private String website;
}
