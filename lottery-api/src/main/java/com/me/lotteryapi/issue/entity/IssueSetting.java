package com.me.lotteryapi.issue.entity;

import com.me.lotteryapi.game.entity.Game;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


/**
 * @program: lottery-study
 * @description: 期号设置
 * @author:
 * @create: 2019-07-13 16:53
 */
@Data
@Setter
@Getter
public class IssueSetting {

    private String id;
    /**
     * 日期格式
     */
    private String dateFormat;

    /**
     * 期号格式
     */
    private String issueFormat;

    /**
     * 对应彩票id
     */
    private String gameId;

    /**
     * 彩种
     */
    private Game game;

    /**
     * 彩期生成规则
     */
    private Set<IssueGenerateRule> issueGenerateRules;

}
