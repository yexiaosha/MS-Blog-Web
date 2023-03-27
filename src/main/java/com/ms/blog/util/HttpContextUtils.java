package com.ms.blog.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Http组件
 * @author wyh
 * @date 2022/11/23 14:10
 */
public class HttpContextUtils {

    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(requestAttributes)){
            return null;
        }
        return requestAttributes.getRequest();

    }

    public static HttpServletResponse getHttpServletResponse() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Objects.requireNonNull(servletRequestAttributes).getResponse();
    }
}
