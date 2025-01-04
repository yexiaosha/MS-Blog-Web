package com.ms.blog.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 页面分页参数
 * @author wyh
 * @date 2022/12/01 11:59
 */
@Data
@Schema(name ="分页参数")
public class PageParam {

    @NotNull
    @Schema(name ="当前行")
    private int beginLine;

    @NotBlank
    @Schema(name ="每页数据量")
    private Integer pageSize;

    @NotBlank
    @Schema(name ="当前页码")
    private Integer currentPage = 0;
}
