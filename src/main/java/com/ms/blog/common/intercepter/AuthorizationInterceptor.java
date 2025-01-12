package com.ms.blog.common.intercepter;

import com.alibaba.fastjson.JSON;
import com.ms.blog.common.ErrorCode;
import com.ms.blog.common.Result;
import com.ms.blog.common.UserThreadLocal;
import com.ms.blog.entity.User;
import com.ms.blog.service.UserService;
import com.ms.blog.util.ResultUtils;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 授权拦截器
 * @author wyh
 * @date 2022/11/21 17:29
 */

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    /**
     * 登录拦截器
     * @param request  请求
     * @param response 请求
     * @param handler  处理器
     * @return 是否拦截
     * @throws Exception 拦截器时异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String token = request.getHeader("Authorization");
        if (token == null) {
            Result<Boolean> result = ResultUtils.fail(ErrorCode.ACCOUNT_NOT_AUTHORIZATION.getCode(),
                    ErrorCode.ACCOUNT_NOT_AUTHORIZATION.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        User user = userService.checkToken(token).getData();
        if (user == null) {
            Result<Boolean> result = ResultUtils.fail(ErrorCode.ACCOUNT_NOT_AUTHORIZATION.getCode(),
                    ErrorCode.ACCOUNT_NOT_AUTHORIZATION.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        UserThreadLocal.put(user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        UserThreadLocal.remove();
    }
}
