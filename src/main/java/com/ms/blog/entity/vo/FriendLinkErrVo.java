package com.ms.blog.entity.vo;

import com.ms.blog.entity.FriendLink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 友情链接检验错误视图类
 * @author wyh
 * @date 2023/02/03 15:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FriendLinkErrVo extends FriendLink {
    private String errMessage;
}
