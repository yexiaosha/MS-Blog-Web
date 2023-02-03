package com.ms.blog.entity.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 友情链接数据传输层
 * @author wyh
 * @date 2023/02/03 17:50
 */
@Data
@Builder
@NoArgsConstructor
public class FriendLinkDto {
    private String name;
    private String info;
    private String email;
    private String url;
}
