package com.ms.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 简单文章信息视图类
 * @author wyh
 * @date 2023/02/09 15:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("简单文章信息视图类")
public class ArticleSimpleVo {
    @ApiModelProperty("文章id")
    private Integer id;

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章简介")
    private String summary;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("创造时间")
    private Date createTime;

    @ApiModelProperty("作者昵称")
    private String nikeName;

    @ApiModelProperty("文章头像")
    private String avatar;

    @ApiModelProperty("作者用户名")
    private String username;
}
