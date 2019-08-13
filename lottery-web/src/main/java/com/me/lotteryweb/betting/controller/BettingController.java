package com.me.lotteryweb.betting.controller;

import com.me.lotteryapi.betting.param.PlaceOrderParam;
import com.me.lotteryapi.common.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: lottery-study
 * @description: 投注接口
 * @author:
 * @create: 2019-08-13 16:59
 */
@RestController
@RequestMapping("/betting")
public class BettingController {

    /**
     * 下单
     *
     * @return
     */
    @PostMapping("/placeOrder")
    public Result placeOrder(@Validated @RequestBody PlaceOrderParam placeOrderParam) {
        System.out.println(placeOrderParam);
        return Result.success();
    }

}
