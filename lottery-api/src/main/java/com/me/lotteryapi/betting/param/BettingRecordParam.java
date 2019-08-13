package com.me.lotteryapi.betting.param;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: lottery-study
 * @description: 投注记录入参
 * @author:
 * @create: 2019-08-13 17:12
 */
@Data
public class BettingRecordParam {
    /**
     * 游戏玩法代码
     */
    @NotBlank
    private String gamePlayCode;

    /**
     * 所选号码
     */
    @NotBlank
    private String selectedNo;

    /**
     * 注数
     */
    @NotNull
    @DecimalMin(value = "1", inclusive = true)
    private Long bettingCount;

}
