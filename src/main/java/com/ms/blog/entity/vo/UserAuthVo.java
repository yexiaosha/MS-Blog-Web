package com.ms.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 用户详细信息视图类
 * @author wyh
 * @date 2023/01/13 09:55
 */
@Data
@Builder
@Schema(name ="用户详细信息视图类")
public class UserAuthVo {
    /**
     * 用户id
     */
    @Schema(name = "用户id")
    private Integer id;

    /**
     * 邮箱
     */
    @Schema(name = "邮箱")
    private String email;

    /**
     * 用户昵称
     */
    @Schema(name = "用户昵称")
    private String nikeName;

    /**
     * 头像链接
     */
    @Schema(name = "头像链接")
    private String avatar;

    /**
     *  用户简介
     */
    @Schema(name = "用户简介")
    private String intro;

    /**
     *  个人网站
     */
    @Schema(name = "个人网站")
    private String website;

    /**
     * 是否禁用
     */
    @Schema(name = "是否禁用")
    private Integer isDisabled;

    /**
     * 创建时间
     */
    @Schema(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(name = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;
}
