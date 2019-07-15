package com.me.lotteryapi.issue.dao;

import com.me.lotteryapi.issue.entity.Issue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface IssueMapper {
    List<Issue> getIssueByGameCodeAndLotteryDate(@Param("gameCode") String gameCode,@Param("lotteryDate") Date lotteryDate);

    Integer save(Issue issue);
}
