package com.ms.blog.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注销请求
 * @author wyh
 * @date 2023/01/13 16:55
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cancellation {
    private Integer id;
    private Integer userId;
    private String username;
    private String email;
    private Date createTime;
    private String reasonText;
}
