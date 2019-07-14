package com.we.lotterytask.issuetask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @program: lottery-study
 * @description: 彩期生成任务
 * @author:
 * @create: 2019-07-13 17:08
 */
@Component
@Slf4j
public class GenerateIssueTask {

    @Scheduled(cron = "*/10 * * * * ?")
    public void execute(){
        System.out.println("-------------------");
    }

}
