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
public class SyncLotteryNumMsg {
    public SyncLotteryNumMsg() {
    }

    public SyncLotteryNumMsg(String gameCode, Long issueNum, Integer retries) {
        this.gameCode = gameCode;
        this.issueNum = issueNum;
        this.retries = retries;
    }

    private String gameCode;

    private Long issueNum;

    private Integer retries;

}
