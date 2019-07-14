package com.me.lotteryapi.issue.dao;

import com.me.lotteryapi.issue.entity.IssueGenerateRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * @program: lottery-study
 * @description:
 * @author:
 * @create: 2019-07-14 14:37
 */
@Mapper
public interface IssueGenerateRuleMapper {
    Set<IssueGenerateRule> getIssueGenerateRuleByIssueSettingId(String id);
}
