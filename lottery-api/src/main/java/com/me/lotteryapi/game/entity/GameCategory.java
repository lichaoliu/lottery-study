package com.me.lotteryapi.game.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @program: lottery-study
 * @description: 彩种类别
 * @author:
 * @create: 2019-07-14 14:31
 */
@Data
@Getter
@Setter
public class GameCategory {

    /**
     * 主键id
     */
    private String id;

    /**
     * 游戏类别code
     */
    private String gameCategoryCode;

    /**
     * 游戏类别
     */
    private String gameCategoryName;

    /**
     * 排序号
     */
    private Double orderNo;

    /**
     * 游戏集合
     */
    private Set<Game> games;
}
