package com.me.lotteryapi.issue.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.me.lotteryapi.common.utils.IdUtils;
import com.me.lotteryapi.constants.Constant;
import com.me.lotteryapi.issue.dao.IssueMapper;
import com.me.lotteryapi.issue.entity.Issue;
import com.me.lotteryapi.issue.entity.IssueGenerateRule;
import com.me.lotteryapi.issue.entity.IssueSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @program: lottery-study
 * @description: 彩期期次Service
 * @author:
 * @create: 2019-07-15 15:45
 */
@Service
public class IssueService {
    @Autowired
    IssueMapper issueMapper;
    @Autowired
    IssueSettingService issueSettingService;

    public List<Issue> getIssueByGameCodeAndLotteryDate(String gameCode, Date lotteryDate) {
        return issueMapper.getIssueByGameCodeAndLotteryDate(gameCode, lotteryDate);
    }

    public Integer save(Issue issue){
        return issueMapper.save(issue);
    }

//    public Issue getLatelyIssue(String gameCode,String nowTime){
//
//    }

    @Transactional
    public void generateIssue(DateTime currentDate) {
        List<IssueSetting> list = issueSettingService.getIssueSetting();
        for (IssueSetting issueSetting : list) {
            //默认生成5天的期次
            for (int i = 0; i < 5; i++) {
                Date lotteryDate = DateUtil.offset(DateUtil.beginOfDay(currentDate), DateField.DAY_OF_MONTH, i);
                List<Issue> issues = getIssueByGameCodeAndLotteryDate(issueSetting.getGame().getGameCode(), lotteryDate);
                if (!CollectionUtil.isEmpty(issues)) {
                    continue;
                }

                String lotteryDateFormat = DateUtil.format(lotteryDate, issueSetting.getDateFormat());
                Set<IssueGenerateRule> issueGenerateRules = issueSetting.getIssueGenerateRules();
                int count = 0;
                for (IssueGenerateRule issueGenerateRule : issueGenerateRules) {
                    Integer issueCount = issueGenerateRule.getIssueCount();
                    for (int j = 0; j < issueCount; j++) {
                        Long issueNum = Long.parseLong(lotteryDateFormat + String.format(issueSetting.getIssueFormat(), count + j + 1));
                        long issueNumInner = count + j + 1;
                        DateTime dateTime = DateUtil.parse(issueGenerateRule.getStartTime(), "hh:mm");
                        Date startTime = DateUtil.offset(lotteryDate, DateField.MINUTE,
                                dateTime.hour(true) * 60 + dateTime.minute() + j * issueGenerateRule.getTimeInterval());
                        Date endTime = DateUtil.offset(startTime, DateField.MINUTE,
                                issueGenerateRule.getTimeInterval());
                        Issue issue = new Issue();
                        issue.setId(IdUtils.getId());
                        issue.setGameCode(issueSetting.getGame().getGameCode());
                        issue.setLotteryDate(lotteryDate);
                        issue.setLotteryTime(endTime);
                        issue.setIssueNum(issueNum);
                        issue.setIssueNum(issueNumInner);
                        issue.setStartTime(startTime);
                        issue.setEndTime(endTime);
                        issue.setState(Constant.期号状态_未开奖);
                        issue.setAutomaticLottery(true);
                        issue.setAutomaticSettlement(true);
                        save(issue);
                    }
                    count += issueCount;
                }
            }
        }

    }
}
