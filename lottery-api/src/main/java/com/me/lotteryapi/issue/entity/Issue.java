package com.me.lotteryapi.issue.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @program: lottery-study
 * @description: 彩期期次
 * @author:
 * @create: 2019-07-15 15:48
 */
@Data
@Setter
@Getter
public class Issue {

    /**
     * 主键id
     */
    private String id;

    /**
     * 所属游戏代码
     */
    private String gameCode;

    /**
     * 期数
     */
    private Long issueNum;

    /**
     * 期数(内部用)
     */
    private Long issueNumInner;

    /**
     * 开奖日期,0点整
     */
    private Date lotteryDate;

    /**
     * 开奖时间
     */
    private Date lotteryTime;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 全部开奖号码,以逗号分隔
     */
    private String lotteryNum;

    /**
     * 同步时间
     */
    private Date syncTime;

    /**
     * 结算时间
     */
    private Date settlementTime;

    /**
     * 状态
     */
    private String state;

    /**
     * 自动开奖
     */
    private Boolean automaticLottery;

    /**
     * 自动结算
     */
    private Boolean automaticSettlement;

    /**
     * 乐观锁版本号
     */
    private Long version;

}
