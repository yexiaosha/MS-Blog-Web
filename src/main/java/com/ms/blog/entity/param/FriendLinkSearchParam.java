package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 友情链接表单参数
 * @author wyh
 * @date 2023/02/02 11:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Schema(name ="获取友情链接表单参数")
public class FriendLinkSearchParam extends PageParam {

    @Schema(name ="主键id")
    private Integer id;

    @Schema(name ="网站名称")
    private String name;
}
