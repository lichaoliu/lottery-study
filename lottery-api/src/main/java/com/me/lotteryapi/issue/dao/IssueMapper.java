package com.me.lotteryapi.issue.dao;

import com.me.lotteryapi.issue.entity.Issue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface IssueMapper {
    List<Issue> getIssueByGameCodeAndLotteryDate(@Param("gameCode") String gameCode, @Param("lotteryDate") Date lotteryDate);

    Integer save(Issue issue);

    Issue getCurrentIssue(@Param("gameCode") String gameCode, @Param("now") String now, @Param("now1") String now1);

    Issue getTopIssueByEndTime(@Param("gameCode") String gameCode, @Param("now") String now);

    Issue getTopIssueByIssueNum(@Param("gameCode") String gameCode, @Param("issueNum") Long issueNum);
}
