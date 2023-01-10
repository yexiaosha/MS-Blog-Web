package com.ms.blog.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 友情链接
 * @author wyh
 * @date 2023/01/09 18:24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendLink {

    /**
     * 主键id
     */
    private Integer id;

    /**
     *  网站名称
     */
    private String name;

    /**
     *  网站链接
     */
    private String url;

    /**
     * 网站头像地址
     */
    private String avatar;

    /**
     * 网站信息
     */
    private String info;

    /**
     * 邮箱
     */
    private String email;

    /**
     *  下架原因
     */
    private String reason;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态  0待审核 1通过
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     *  更新时间
     */
    private Date updateTime;
}
