package com.me.lotteryapi.common.exception;

/**
 * @program: lottery-study
 * @description: 自定义业务异常
 * @author:
 * @create: 2019-08-15 14:38
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;

    private String msg;

    public BizException(BizError bizError) {
        super(bizError.getMsg());
        this.code = bizError.getCode();
        this.msg = bizError.getMsg();
    }

    public BizException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}
