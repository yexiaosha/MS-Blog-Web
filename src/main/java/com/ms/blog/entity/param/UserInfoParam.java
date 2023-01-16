package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 用户信息参数
 * @author wyh
 * @date 2023/01/16 10:10
 */
@Data
@ApiModel("用户信息参数")
public class UserInfoParam {
    private String nickname;
    private String avatar;
    private String intro;
    private String website;
}
