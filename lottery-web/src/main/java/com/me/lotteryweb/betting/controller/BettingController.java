package com.me.lotteryweb.betting.controller;

import com.me.lotteryapi.betting.param.PlaceOrderParam;
import com.me.lotteryapi.betting.service.BettingService;
import com.me.lotteryapi.common.vo.Result;
import com.me.lotteryweb.config.security.UserAccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: lottery-study
 * @description: 投注接口
 * @author:
 * @create: 2019-08-13 16:59
 */
@RestController
@RequestMapping("/betting")
public class BettingController {

    @Autowired
    private BettingService bettingService;

    /**
     * 下单
     *
     * @return
     */
    @PostMapping("/placeOrder")
    public Result placeOrder(@Validated @RequestBody PlaceOrderParam placeOrderParam) {
        UserAccountDetails user = null;
        bettingService.placeOrder(placeOrderParam, user.getUserAccountId());



        System.out.println(placeOrderParam);
        return Result.success();
    }

}
