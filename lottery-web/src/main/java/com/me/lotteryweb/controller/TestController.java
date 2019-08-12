package com.me.lotteryweb.controller;

import com.me.lotteryapi.dictconfig.DictHolder;
import com.me.lotteryapi.issue.dao.IssueMapper;
import com.me.lotteryapi.issue.entity.Issue;
import com.me.lotteryapi.issue.service.IssueService;
import com.me.lotteryapi.issue.vo.IssueVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @program: lottery-study
 * @description:
 * @author:
 * @create: 2019-07-13 11:20
 */
@RestController
public class TestController {


    @Autowired
    IssueService issueService;

    @Autowired
    IssueMapper issueMapper;


    @PostMapping("/placeOrder")
    public String test(){
        Issue issue = new Issue();
        issue.syncLotteryNum("111");
        issue.setId("111");
        issue.setGameCode("bb");
        issue.setIssueNum(123L);
        issue.setIssueNumInner(123L);
        issue.setLotteryDate(new Date());

        Integer num = issueMapper.updateIssue(issue);
        System.out.println(num);

        return "test";
    }


    public static void  main(String[] argus){


//        DateTime dateTime = DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, 0);
////        DateTime dateTime1 = DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, 0);
//        DateTime begin = DateUtil.beginOfDay(dateTime);
//        String lotteryDateFormat = DateUtil.format(begin, "yyyyMMdd");
//        System.out.println(lotteryDateFormat);
//        long issueNum = Long.parseLong(
//                lotteryDateFormat + String.format("%03d",  1));
//
//        System.out.println(issueNum);
//
//        DateTime dateTime3 = DateUtil.parse("00:10", "hh:mm");
//        System.out.println(dateTime3);
//        Date startTime = DateUtil.offset(begin, DateField.MINUTE,
//                dateTime3.hour(true) * 60 + dateTime3.minute() + 20);
//        System.out.println(begin);
//        System.out.println("startTime--"+startTime);
//
//        Date endTime = DateUtil.offset(startTime, DateField.MINUTE,
//                20);

//        System.out.println("-----endTime---"+endTime);
    }
}
