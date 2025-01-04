package com.ms.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 友情链接视图类
 * @author wyh
 * @date 2023/02/02 11:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name ="友情链接视图类")
public class FriendLinkVo {
    /**
     * 主键id
     */
    @Schema(name ="主键id")
    private Integer id;

    /**
     *  网站名称
     */
    @Schema(name ="网站名称")
    private String name;

    /**
     *  网站链接
     */
    @Schema(name ="网站链接")
    private String url;

    /**
     * 网站头像地址
     */
    @Schema(name ="网站头像地址")
    private String avatar;

    /**
     * 网站信息
     */
    @Schema(name ="网站信息")
    private String info;

    /**
     * 邮箱
     */
    @Schema(name ="邮箱")
    private String email;

    /**
     *  下架原因
     */
    @Schema(name ="下架原因")
    private String reason;

    /**
     * 排序
     */
    @Schema(name ="排序")
    private Integer sort;

    /**
     * 状态  0待审核 1通过
     */
    @Schema(name ="状态  0待审核 1通过")
    private Integer status;

    /**
     * 创建时间
     */
    @Schema(name ="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date creatTime;

    /**
     *  更新时间
     */
    @Schema(name ="更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;
}
