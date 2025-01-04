package com.ms.blog.entity.param;

import com.ms.blog.common.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户日志参数类
 * @author wyh
 * @date 2023/03/07 11:07
 */
@Data
@Schema(name = "用户日志参数类")
@EqualsAndHashCode(callSuper = true)
public class UserLogParam extends PageParam {

    @Schema(name = "用户名")
    private String username;

    @Schema(name ="操作系统")
    private String os;

    @Schema(name ="ip地址")
    private String ip;

    @Schema(name ="请求类型")
    private String type;

    @Schema(name ="操作结果")
    private String result;

    @Schema(name ="日志生成时间")
    private Date createTime;

    @Schema(name ="浏览器")
    private String browser;

    @Schema(name ="客户端类型")
    private String clientType;
}
