package com.ms.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "简单文章信息视图类")
public class ArticleSimpleVo {
    @Schema(name = "文章id")
    private Integer id;

    @Schema(name = "文章标题")
    private String title;

    @Schema(name = "文章简介")
    private String summary;

    @Schema(name = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;

    @Schema(name = "创造时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    @Schema(name = "作者昵称")
    private String nikeName;

    @Schema(name = "文章头像")
    private String avatar;

    @Schema(name = "作者用户名")
    private String username;

    @Schema(name = "归档名称(分类或标签名)")
    private String archiveName;
}
