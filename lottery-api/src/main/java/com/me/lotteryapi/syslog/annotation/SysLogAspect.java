package com.me.lotteryapi.syslog.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @program: lottery-study
 * @description: 日志切面实现
 * @author:
 * @create: 2019-07-15 14:39
 */
@Aspect
@Slf4j
@Component
public class SysLogAspect {
    @Pointcut("@annotation(com.me.lotteryapi.syslog.annotation.SysLog)")
    public void pointCut(){}

    @After("pointCut()")
    public void poinCutAfter(JoinPoint joinPoint){
        log.error("-----------SysLogAspect-----------");
        buildLog(joinPoint);
    }

    private void buildLog(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SysLog sysLog = signature.getMethod().getAnnotation(SysLog.class);
        String logName = sysLog.logName();
        String optType = sysLog.optType();
        System.out.println("--logName--"+logName+"--optType---"+optType);
    }
}
