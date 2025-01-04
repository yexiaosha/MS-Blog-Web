package com.ms.blog.util;

import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 系统类
 * @author wyh
 * @date 2023/01/11 17:54
 */
public class SystemUtil {
    public static String getOs(HttpServletRequest request){
        String agent = request.getHeader("user-agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        return userAgent.getOperatingSystem().getName();
    }

    public static String getBrowser(HttpServletRequest request){
        String agent = request.getHeader("user-agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        return userAgent.getBrowser().getName();
    }

    public static String getClientType(HttpServletRequest request){
        String agent = request.getHeader("user-agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        return userAgent.getOperatingSystem().getDeviceType().getName();
    }
}
