package com.ms.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 上传结果
 *
 * @author gary
 * @date 2023/3/28 16:29
 */
@Data
@ApiModel("上传结果")
public class QiniuVo {
    private String path;
}
