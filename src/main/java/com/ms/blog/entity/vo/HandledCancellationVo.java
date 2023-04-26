package com.ms.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("已处理注销请求")
public class HandledCancellationVo {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("注销请求id")
    private Integer cancellationId;

    @ApiModelProperty("发起用户id")
    private Integer userId;

    @ApiModelProperty("注销请求发起时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date cancellationCreateTime;

    @ApiModelProperty("处理时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date handleDate;

    @ApiModelProperty("处理人")
    private String handler;

    @ApiModelProperty("处理结果")
    private Integer result;

    @ApiModelProperty("处理建议")
    private String resultSuggest;

    @ApiModelProperty("处理者用户id")
    private Integer handlerId;
}
