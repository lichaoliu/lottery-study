package com.me.lotteryapi.issue.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: lottery-study
 * @description:
 * @author:
 * @create: 2019-08-01 13:21
 */
@Data
@AllArgsConstructor
public class SyncLotteryNumMsg {

    private String gameCode;

    private Long issueNum;

    private Integer retries;

}
