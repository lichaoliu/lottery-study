package com.me.lotterytask.issuetask;

import com.alibaba.fastjson.JSON;
import com.me.lotteryapi.game.entity.Game;
import com.me.lotteryapi.game.entity.GameCategory;
import com.me.lotteryapi.game.service.GameCategoryService;
import com.me.lotteryapi.game.service.GameService;
import com.me.lotteryapi.issue.entity.IssueSetting;
import com.me.lotteryapi.issue.service.IssueSettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

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

    /**
     * 生成彩票期次
     */
    @Scheduled(cron = "*/10 * * * * ?")
    public void execute(){
        List<IssueSetting> list =  issueSettingService.getIssueSetting();

        System.out.println("============="+ JSON.toJSON(list));

        System.out.println(list);

    }

}
