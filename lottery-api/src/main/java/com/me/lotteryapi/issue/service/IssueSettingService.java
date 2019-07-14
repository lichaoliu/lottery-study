package com.me.lotteryapi.issue.service;

import com.me.lotteryapi.game.entity.Game;
import com.me.lotteryapi.game.service.GameService;
import com.me.lotteryapi.issue.dao.issueSettingMapper;
import com.me.lotteryapi.issue.entity.IssueSetting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @program: lottery-study
 * @description:
 * @author:
 * @create: 2019-07-14 10:01
 */
@Service
@Validated
@Slf4j
public class IssueSettingService {

    @Autowired
    issueSettingMapper issueSettingMapper;

    @Autowired
    IssueGenerateRuleService issueGenerateRuleService;

    @Autowired
    GameService gameService;

    public List<IssueSetting> getIssueSetting() {
        List<IssueSetting> issueSettings = issueSettingMapper.getIssueSetting();
        return issueSettings;
    }
}
