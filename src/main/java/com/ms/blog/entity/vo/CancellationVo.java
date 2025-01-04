package com.ms.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 注销请求表单视图类
 * @author wyh
 * @date 2023/01/13 17:31
 */
@Data
@Schema(name = "注销请求表单视图类")
@Builder
@AllArgsConstructor
public class CancellationVo {
    @Schema(name = "主键id")
    private Integer id;

    @Schema(name = "用户id")
    private Integer userId;

    @Schema(name = "用户名")
    private String username;

    @Schema(name = "邮箱")
    private String email;

    @Schema(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    @Schema(name = "理由")
    private String reasonText;
}
