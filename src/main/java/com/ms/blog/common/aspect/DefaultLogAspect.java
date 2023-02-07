package com.ms.blog.common.aspect;

import com.alibaba.fastjson.JSON;
import com.ms.blog.common.annotation.ControllerLog;
import com.ms.blog.common.annotation.ServiceLog;
import com.ms.blog.entity.ExceptionLog;
import com.ms.blog.entity.User;
import com.ms.blog.entity.UserLog;
import com.ms.blog.service.LogService;
import com.ms.blog.util.HttpContextUtils;
import com.ms.blog.util.IpUtils;
import com.ms.blog.util.JwtUtils;
import com.ms.blog.util.SystemUtil;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 日志切点类
 * @author wyh
 * @date 2022/11/23 10:17
 */

@Aspect
@Component
@Order(0)
@Slf4j
public class DefaultLogAspect {

    @Resource
    private LogService logService;

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(DefaultLogAspect.class);

    private UserLog userLog;

    @Pointcut("@annotation(com.ms.blog.common.annotation.ServiceLog)")
    public void serviceAspect() {
    }

    @Pointcut("@annotation(com.ms.blog.common.annotation.ControllerLog)")
    public void controllerAspect() {
    }

    /**
     * 前置通知，拦截controller层记录用户的操作
     * @param joinPoint 切入点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String token = request.getHeader("Authorization");
        String method = request.getMethod();
        String ip = IpUtils.getIpAddress(request);
        String username = getUsername(token);
        ControllerLog controllerLog = ((MethodSignature) (joinPoint.getSignature())).getMethod()
                .getAnnotation(ControllerLog.class);
        String model = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
        this.userLog = new UserLog();
        if (controllerLog != null) {
            String description = controllerLog.value();
            userLog.setDescription(description);
        }
        try {
            logger.info("===============前置通知开始===============");
            logger.info("请求类型：Controller接口" + method);
            logger.info("请求方法：" + model);
            logger.info("方法描述：" + userLog.getDescription());
            if (username == null) {
                username = "游客";
            }
            logger.info("请求用户：" + username);
            logger.info("请求ip：" + ip);

            userLog.setAddress(IpUtils.getIpLocation());
            userLog.setBrowser(SystemUtil.getBrowser(request));
            userLog.setAccessOs(SystemUtil.getOs(request));
            userLog.setUsername(username);
            userLog.setIp(ip);
            userLog.setModel(model);
            userLog.setType(method);
            userLog.setClientType(SystemUtil.getClientType(request));
            userLog.setCreateTime(new Date());

        } catch (Exception e) {
            logger.error("========前置通知代码异常========");
            logger.error("异常信息：{}", e.getMessage());
        }
    }

    @AfterReturning(pointcut = "controllerAspect()")
    public void doAfter() {
        logger.info("=====请求完成=====");
        this.userLog.setResult("成功");
        logService.insertUserLog(userLog);
    }

    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(Throwable e){
        logger.info("请求失败,错误为："+ e.getMessage());
        this.userLog.setResult("失败");
        logService.insertUserLog(userLog);
    }

    /**
     * Service异常处理通知
     * @param joinPoint 切入点
     * @param e         所导致的异常类
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String token = request.getHeader("Authorization");
        String name = getUsername(token);
        ServiceLog serviceLog = ((MethodSignature) (joinPoint.getSignature())).getMethod()
                .getAnnotation(ServiceLog.class);
        String ip = IpUtils.getIpAddress(request);
        String method = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
        String message = serviceLog.value();
        ExceptionLog exceptionLog = new ExceptionLog();
        exceptionLog.setOperation(message);
        StringBuilder params = new StringBuilder();
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params.append(JSON.toJSONString(joinPoint.getArgs()[i])).append(";");
            }
        }

        if (name == null){
            name = "游客";
        }

        try {
            logger.info("========异常日志========");
            logger.info("执行代码：" + e.getClass().getName());
            logger.info("执行信息：" + e.getMessage());
            logger.info("执行方法：" + method + "()");
            logger.info("方法描述：" + message);
            logger.info("请求人：" + name);
            logger.info("请求ip：" + ip);
            logger.info("请求参数：" + params);
            exceptionLog.setParams(String.valueOf(params));
            exceptionLog.setIp(ip);
            exceptionLog.setMethod(method);
            exceptionLog.setExceptionJson(JSON.toJSONString(e));
            exceptionLog.setExceptionMessage(e.getMessage());
            exceptionLog.setUsername(getUsername(token));
            exceptionLog.setSource(IpUtils.getIpLocation());
            exceptionLog.setCreateTime(new Date());
            logService.insertExceptionLog(exceptionLog);
        } catch (Exception ex) {
            logger.error("=====异常通知代码异常=====");
            logger.error("异常信息:{}", ex.getMessage());
        }
    }

    public String getUsername(String token){
        Map<String, Object> map = JwtUtils.verifyToken(token);
        if (map == null){
            return null;
        }

        String userJson = (String) redisTemplate.opsForValue().get("TOKEN_" + token);
        if (!(StringUtils.hasText(userJson))){
            return null;
        }
        User user = JSON.parseObject(userJson, User.class);
        return user.getUsername();
    }
}