package com.ms.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 整合包实体
 *
 * @author wyh
 * @date 2023/01/12 18:33
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModPack {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 整合包名字
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 整合包状态 0正式上线 1测试中 2停更
     */
    private Integer status;

    /**
     * 整合包完成进度
     */
    private Integer overallProgress;

    /**
     * 整合包介绍
     */
    private String introduce;

    /**
     * 版本号
     */
    private String version;
}
