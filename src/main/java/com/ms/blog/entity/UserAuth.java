package com.ms.blog.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户详情
 * @author wyh
 * @date 2023/01/09 18:30
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {

    /**
     * 用户详情id
     */
    private Integer id;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 头像链接
     */
    private String avatar;

    /**
     *  用户简介
     */
    private String intro;

    /**
     *  个人网站
     */
    private String website;

    /**
     * 是否禁用
     */
    private Integer isDisabled;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
