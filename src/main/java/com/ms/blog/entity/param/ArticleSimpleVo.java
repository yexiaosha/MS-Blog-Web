package com.ms.blog.entity.param;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 简单文章信息视图类
 * @author wyh
 * @date 2023/02/09 15:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleSimpleVo {
    private Integer id;
    private String title;
    private String summary;
    private Date updateDate;
    private Date createDate;
    private String nikeName;
    private String username;
}
