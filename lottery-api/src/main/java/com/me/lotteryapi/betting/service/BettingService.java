package com.me.lotteryapi.betting.service;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.me.lotteryapi.betting.param.PlaceOrderParam;
import com.me.lotteryapi.common.exception.BizError;
import com.me.lotteryapi.common.exception.BizException;
import com.me.lotteryapi.issue.service.IssueService;
import com.me.lotteryapi.issue.vo.IssueVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @program: lottery-study
 * @description:
 * @author:
 * @create: 2019-08-13 17:17
 */
public class BettingService {

    @Autowired
    private IssueService issueService;

    public String placeOrder(PlaceOrderParam placeOrderParam, String userAccountId) {
        Date now = new Date();
        IssueVO currentIssue = issueService.getCurrentIssue(placeOrderParam.getGameCode());
        IssueVO bettingIssue = issueService.getIssueByGameCodeAndIssueNum(placeOrderParam.getGameCode(), placeOrderParam.getIssueNum());
        if (currentIssue == null) {
            throw new BizException(BizError.休市中);
        }
        if (currentIssue.getIssueNum().equals(placeOrderParam.getIssueNum())) {
            long second = DateUtil.between(currentIssue.getEndTime(), now, DateUnit.SECOND);
            Long timeCompare = currentIssue.getEndTime().getTime() - now.getTime();
            if (second <= 30 || timeCompare < 0) {
                throw new BizException(BizError.已截止投注);
            }
        } else {
            if (bettingIssue == null) {
                throw new BizException(BizError.期号非法);
            }
            if (bettingIssue.getLotteryDate().getTime() < currentIssue.getLotteryDate().getTime()) {
                throw new BizException(BizError.期号非法);
            }
            if (bettingIssue.getLotteryDate().getTime() > currentIssue.getLotteryDate().getTime()) {
                throw new BizException(BizError.只能追当天的号);
            }
            if (bettingIssue.getIssueNum() < currentIssue.getIssueNum()) {
                throw new BizException(BizError.该期已封盘无法投注);
            }
        }
        return null;
    }
}
