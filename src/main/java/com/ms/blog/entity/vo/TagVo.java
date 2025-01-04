package com.ms.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 文章标签视图类
 * @author wyh
 * @date 2023/01/16 14:12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "文章标签视图类")
public class TagVo {
    /**
     * 主键id
     */
    @Schema(name = "主键id")
    private Integer id;

    /**
     *  标签名称
     */
    @Schema(name = "标签名称")
    private String name;

    /**
     * 点击量
     */
    @Schema(name = "点击量")
    private Integer clickVolume;

    /**
     * 排序
     */
    @Schema(name = "排序")
    private Integer sort;

    /**
     * 创建时间
     */
    @Schema(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @Schema(name = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;
}
