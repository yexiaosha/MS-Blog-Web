package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 错误日志参数类
 * @author wyh
 * @date 2023/03/09 15:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name ="错误日志参数类")
public class ExceptionLogParam extends PageParam {

    /**
     *  操作用户
     */
    @Schema(name ="操作用户名")
    private String username;

    /**
     * ip
     */
    @Schema(name ="ip")
    private String ip;

    /**
     * ip来源
     */
    @Schema(name ="ip来源")
    private String source;

    /**
     * 请求方法
     */
    @Schema(name ="请求方法")
    private String method;

    /**
     * 发生时间
     */
    @Schema(name ="发生时间")
    private Date createTime;
}
