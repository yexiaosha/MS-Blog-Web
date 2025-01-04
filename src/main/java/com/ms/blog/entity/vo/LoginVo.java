package com.ms.blog.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 登陆返回视图类
 * @author wyh
 * @date 2023/01/11 17:02
 */
@Data
@Schema(name ="登录返回视图类")
public class LoginVo {

    @Schema(name ="生成token")
    private String token;

    @Schema(name ="用户类型")
    private String role;

    @Schema(name ="用户简单信息")
    private UserSimpleVo userSimpleVo;
}
