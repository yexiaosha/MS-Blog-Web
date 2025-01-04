package com.ms.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 已处理注销请求
 * @author wyh
 * @date 2023/01/13 18:09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "已处理注销请求")
public class HandledCancellationVo {

    @Schema(name = "主键id")
    private Integer id;

    @Schema(name = "注销请求id")
    private Integer cancellationId;

    @Schema(name = "发起用户id")
    private Integer userId;

    @Schema(name = "注销请求发起时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date cancellationCreateTime;

    @Schema(name = "处理时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date handleDate;

    @Schema(name = "处理人")
    private String handler;

    @Schema(name = "处理结果")
    private Integer result;

    @Schema(name = "处理建议")
    private String resultSuggest;

    @Schema(name = "处理者用户id")
    private Integer handlerId;
}
