package com.me.lotteryapi.game.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: lottery-study
 * @description: 彩种
 * @author:
 * @create: 2019-07-14 14:22
 */
@Data
@Getter
@Setter
public class Game {

    /**
     * 主键id
     */
    private String id;

    /**
     * 游戏代码
     */
    private String gameCode;

    /**
     * 游戏名称
     */
    private String gameName;

    /**
     * 游戏说明
     */
    private String gameDesc;

    /**
     * 是否为热门彩种
     */
    private Boolean hotGameFlag;

    /**
     * 状态,启用:1;禁用:0
     */
    private String state;

    /**
     * 排序号
     */
    private Double orderNo;

    /**
     * 对应游戏类别id
     */
    private String gameCategoryId;

    /**
     * 彩种类别
     */
    private GameCategory gameCategory;

}
