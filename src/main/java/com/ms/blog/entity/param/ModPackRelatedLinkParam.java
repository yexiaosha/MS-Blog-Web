package com.ms.blog.entity.param;

import lombok.Data;

/**
 * 相关链接参数类
 * @author wyh
 * @date 2023/02/07 15:09
 */
@Data
public class ModPackRelatedLinkParam {
    private Integer modPackId;
    private String name;
    private String pic;
    private String url;
}
