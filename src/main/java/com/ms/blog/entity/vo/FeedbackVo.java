package com.ms.blog.entity.vo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 反馈视图类
 * @author wyh
 * @date 2023/02/13 10:37
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackVo {
    private Integer id;
    private String email;
    private String title;
    private String content;
    private Date createTime;
}
