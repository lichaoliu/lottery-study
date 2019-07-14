package com.me.lotteryapi.issue.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: lottery-study
 * @description: 彩期生成规则
 * @author:
 * @create: 2019-07-14 14:21
 */

@Data
@Getter
@Setter
public class IssueGenerateRule {
    /**
     * 主键id
     */
    private String id;

    /**
     * 开始时间(时分,如10:00)
     */
    private String startTime;

    /**
     * 时间间隔(分钟)
     */
    private Integer timeInterval;

    /**
     * 期数
     */
    private Integer issueCount;

    /**
     * 排序号
     */
    private Double orderNo;

    /**
     * 对应期号设置id
     */
    private String issueSettingId;
}
