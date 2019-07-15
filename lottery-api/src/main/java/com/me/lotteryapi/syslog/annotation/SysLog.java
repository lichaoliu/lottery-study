package com.me.lotteryapi.syslog.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface SysLog {

    String logName();

    String optType();
}
