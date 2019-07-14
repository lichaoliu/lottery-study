package com.me.lotteryapi.issue.service;

import com.me.lotteryapi.issue.dao.IssueGenerateRuleMapper;
import com.me.lotteryapi.issue.entity.IssueGenerateRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @program: lottery-study
 * @description: 彩种生成规则service
 * @author:
 * @create: 2019-07-14 14:36
 */
@Service
public class IssueGenerateRuleService {

    @Autowired
    IssueGenerateRuleMapper issueGenerateRuleMapper;
    /**
     * 根据IssueSettingId获取生成规则
     * @return
     */
    public Set<IssueGenerateRule> getIssueGenerateRuleByIssueSettingId(String id){
        return issueGenerateRuleMapper.getIssueGenerateRuleByIssueSettingId(id);
    }
}
