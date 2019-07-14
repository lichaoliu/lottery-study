package com.me.lotteryapi.issue.dao;

import com.me.lotteryapi.issue.entity.IssueSetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: lottery-study
 * @description:
 * @author:
 * @create: 2019-07-14 14:04
 */
@Mapper
public interface issueSettingMapper {
    List<IssueSetting> getIssueSetting();
}
