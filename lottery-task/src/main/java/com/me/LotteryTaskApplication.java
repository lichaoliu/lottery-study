package com.me;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.me.lotteryapi.issue.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@EnableMethodCache(basePackages="com.me")
@EnableCreateCacheAnnotation
@SpringBootApplication
public class LotteryTaskApplication  implements ApplicationRunner{

    @Autowired
    IssueService issueService;

    public static void main(String[] args) {
        SpringApplication.run(LotteryTaskApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        issueService.generateIssue(DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, 0));
    }
}
