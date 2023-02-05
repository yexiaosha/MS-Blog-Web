package com.ms.blog.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 整合包视图类
 *
 * @author wyh
 * @date 2023/02/05 15:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModPackVo{
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
    private Integer introduce;

    /**
     * 相关链接
     */
    private List<ModPackRelatedLinkVo> relatedLinkVoList;

    /**
     * 版本号
     */
    private String version;

    /**
     * 制作人表
     */
    private List<String> creatorList;
}
