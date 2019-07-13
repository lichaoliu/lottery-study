package com.we.lotteryweb.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @program: lottery-study
 * @description:
 * @author:
 * @create: 2019-07-13 11:20
 */
@RestController
public class TestController {

    @PostMapping("/placeOrder")
    public String test(){
        return "tset";
    }

    public static void  main(String[] argus){
        DateTime dateTime = DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, 0);
//        DateTime dateTime1 = DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, 0);
        DateTime begin = DateUtil.beginOfDay(dateTime);
        String lotteryDateFormat = DateUtil.format(begin, "yyyyMMdd");
        System.out.println(lotteryDateFormat);
        long issueNum = Long.parseLong(
                lotteryDateFormat + String.format("%03d",  1));

        System.out.println(issueNum);

        DateTime dateTime3 = DateUtil.parse("00:10", "hh:mm");
        System.out.println(dateTime3);
        Date startTime = DateUtil.offset(begin, DateField.MINUTE,
                dateTime3.hour(true) * 60 + dateTime3.minute() + 20);
        System.out.println(begin);
        System.out.println("startTime--"+startTime);

        Date endTime = DateUtil.offset(startTime, DateField.MINUTE,
                20);

        System.out.println("-----endTime---"+endTime);
    }
}