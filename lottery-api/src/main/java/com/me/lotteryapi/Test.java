package com.me.lotteryapi;

import com.alibaba.fastjson.JSON;
import com.me.lotteryapi.common.exception.BizError;
import com.me.lotteryapi.common.vo.Result;

/**
 * @program: lottery-study
 * @description:
 * @author:
 * @create: 2019-08-13 16:10
 */
public class Test {
    public static void main(String[] argus){
        System.out.println(BizError.下级账号的返点不能大于上级账号.getCode());
        System.out.println(BizError.下级账号的返点不能大于上级账号.getMsg());

        System.out.println(JSON.toJSONString(Result.success()));
    }
}
