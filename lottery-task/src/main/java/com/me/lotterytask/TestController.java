package com.me.lotterytask;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.me.lotteryapi.issue.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @program: lottery-study
 * @description:
 * @author:
 * @create: 2019-07-15 17:56
 */
@RestController
public class TestController {
    @Autowired
    IssueService issueService;
    @PostMapping("/test")
    public String test(){
        issueService.generateIssue(DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, 0));
        return "test";
    }
}
