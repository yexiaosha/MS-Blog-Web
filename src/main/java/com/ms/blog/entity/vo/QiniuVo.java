package com.ms.blog.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 上传结果
 *
 * @author gary
 * @date 2023/3/28 16:29
 */
@Data
@Schema(name = "上传结果")
public class QiniuVo {
    private String path;
}
