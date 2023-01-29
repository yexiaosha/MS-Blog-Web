package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查找用户表单
 * @author wyh
 * @date 2023/01/13 11:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("查找用户表单")
public class UserParam extends PageParam {
    private String username;
    private String ipSource;
    private Integer roleId;
    private String os;
    private String browser;

}
