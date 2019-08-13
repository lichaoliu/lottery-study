package com.me.lotteryapi.betting.param;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: lottery-study
 * @description: 下单入参
 * @author:
 * @create: 2019-08-13 17:07
 */
@Data
public class PlaceOrderParam {
    /**
     * 游戏代码
     */
    @NotBlank
    private String gameCode;

    /**
     * 期号
     */
    @NotNull
    private Long issueNum;

    /**
     * 投注底数金额
     */
    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private Double baseAmount;

    /**
     * 倍数
     */
    @NotNull
    @DecimalMin(value = "1", inclusive = true)
    private Double multiple;

    /**
     * 追号标识
     */
    @NotNull
    private Boolean trackingNumberFlag;

    /**
     * 返点
     */
    @NotNull
    @DecimalMin(value = "0", inclusive = true)
    private Double rebate;

    /**
     * 投注记录集合
     */
    @NotEmpty
    @Valid
    private List<BettingRecordParam> bettingRecords;

}
