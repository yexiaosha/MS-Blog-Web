package com.ms.blog.entity.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 整合包信息表单
 *
 * @author wyh
 * @date 2023/02/05 15:49
 */
@Data
@ApiModel("整合包信息表单")
public class ModPackParam {

    private String name;
    private Integer status;
    private Integer overallProgress;
    private String introduce;
    private String version;
}
