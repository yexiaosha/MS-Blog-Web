package com.ms.blog.entity.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 友情链接表单
 * @author wyh
 * @date 2023/02/02 12:01
 */
@Data
@Schema(name ="友情链接表单")
public class FriendLinkParam {

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
     * 状态  0下架 1上架
     */
   @Schema(name ="状态  0下架 1上架")
    private Integer status;

}
