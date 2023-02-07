package com.ms.blog.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 整合包相关连接
 *
 * @author wyh
 * @date 2023/02/05 15:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModPackRelatedLink {
    private Integer id;
    private Integer modPackId;
    private String name;
    private String url;
    private String pic;
    private Date updateDate;
    private Date createDate;
}
