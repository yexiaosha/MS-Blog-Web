package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 游客注册表单参数
 * @author wyh
 * @date 2023/01/11 16:29
 */
@Data
@ApiModel("游客注册表单参数")
public class RegisterParam {
    private String username;
    private String password;
    private String email;
    private String captcha;
    private String nikeName;

}
