package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 友情链接表单参数
 * @author wyh
 * @date 2023/02/02 11:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("获取友情链接表单参数")
public class FriendLinkSearchParam extends PageParam {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("网站名称")
    private String name;
}
