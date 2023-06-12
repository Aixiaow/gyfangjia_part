package com.liupanshui.educenter.config;

import com.alibaba.fastjson.JSON;
import com.liupanshui.educenter.entity.OperationLog;
import com.liupanshui.educenter.service.OperationLogService;
import com.liupanshui.educenter.utils.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Aspect
@Component
public class OperationLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(OperationLogAspect.class);

    @Autowired
    private OperationLogService operationLogService;

    //AOP的切入编程
    @Pointcut("execution(public * com.liupanshui.educenter.controller..*.*(..))")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String url = request.getRequestURI();
        String method = request.getMethod();
        String params = JSON.toJSONString(request.getParameterMap());
        String ip = IPUtils.getIpAddr(request);
        String userAgent = request.getHeader("Authorization");
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            OperationLog operationLog = new OperationLog();
            operationLog.setUsername(username);
            operationLog.setUrl(url);
            operationLog.setMethod(method);
            operationLog.setParams(params);
            operationLog.setIp(ip);
            operationLog.setUserAgent(userAgent);
            operationLog.setCreateTime(new Date());
            operationLogService.save(operationLog);
        }
        return result;
    }

}
