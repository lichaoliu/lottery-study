package com.me.lotteryapi.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: lottery-study
 * @description: 全局异常处理
 * @author:
 * @create: 2019-07-14 11:48
 */
//@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handleSQLException(HttpServletRequest request, Exception ex) {
        System.out.println("================================");
        return "验证错误";
    }
}
