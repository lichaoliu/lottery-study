package com.me.lotterytask.issuetask;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.me.lotteryapi.game.service.GameCategoryService;
import com.me.lotteryapi.game.service.GameService;
import com.me.lotteryapi.issue.service.IssueService;
import com.me.lotteryapi.issue.service.IssueSettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: lottery-study
 * @description: 彩期生成任务
 * @author:
 * @create: 2019-07-13 17:08
 */
@Component
@Slf4j
public class GenerateIssueTask {

    @Autowired
    IssueSettingService issueSettingService;
    @Autowired
    GameCategoryService gameCategoryService;
    @Autowired
    GameService gameService;
    @Autowired
    IssueService issueService;

    /**
     * 生成彩票期次
     */
    @Scheduled(cron = "0 10 0 * * ?")
    public void execute() {
        try {
            log.info("执行生成期号数据定时任务start");
            issueService.generateIssue(DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, 0));
            log.info("执行生成期号数据定时任务end");
        } catch (Exception e) {
            log.error("执行生成期号数据定时任务发生异常", e);
        }
    }

    public static void main(String[] argus) {
        DateTime dateTime = DateUtil.beginOfDay(new Date());
        DateTime dateTimeBegin = DateUtil.offset(dateTime, DateField.DAY_OF_MONTH, -32);
        Date date = dateTimeBegin.toJdkDate();
        System.out.println(date.toLocaleString());

    }

}
