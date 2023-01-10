package com.ms.blog.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色
 * @author wyh
 * @date 2023/01/09 18:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 角色编码
     */
    private Integer code;

    /**
     *  角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String remarks;

    /**
     *  创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
