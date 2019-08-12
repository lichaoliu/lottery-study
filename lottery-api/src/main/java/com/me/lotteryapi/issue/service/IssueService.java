package com.me.lotteryapi.issue.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.me.lotteryapi.common.utils.IdUtils;
import com.me.lotteryapi.common.utils.ThreadPoolUtils;
import com.me.lotteryapi.constants.Constant;
import com.me.lotteryapi.issue.dao.IssueMapper;
import com.me.lotteryapi.issue.entity.Issue;
import com.me.lotteryapi.issue.entity.IssueGenerateRule;
import com.me.lotteryapi.issue.entity.IssueSetting;
import com.me.lotteryapi.issue.entity.SyncLotteryNumMsg;
import com.me.lotteryapi.issue.vo.IssueVO;
import com.xxl.mq.client.message.XxlMqMessage;
import com.xxl.mq.client.producer.XxlMqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @program: lottery-study
 * @description: 彩期期次Service
 * @author:
 * @create: 2019-07-15 15:45
 */
@Slf4j
@Service
public class IssueService {
    @Autowired
    IssueMapper issueMapper;
    @Autowired
    IssueSettingService issueSettingService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public List<Issue> getIssueByGameCodeAndLotteryDate(String gameCode, Date lotteryDate) {
        return issueMapper.getIssueByGameCodeAndLotteryDate(gameCode, lotteryDate);
    }

    public Integer save(Issue issue){
        return issueMapper.save(issue);
    }

    /**
     * 同步开奖号码
     * @param gameCode
     * @param issueNum
     * @param lotteryNum
     */
    public void syncLotteryNum(String gameCode, Long issueNum, String lotteryNum) {
        Issue issue = issueMapper.getTopIssueByIssueNum(gameCode,issueNum);
        if(issue == null){
            log.error("当前期号没有生成,请检查定时任务是否发生了异常.gameCode:{},issueNum:{}", gameCode, issueNum);
            return;
        }
        if (!Constant.期号状态_未开奖.equals(issue.getState())) {
            return;
        }
        if(!issue.getAutomaticSettlement()){
            log.warn("当前期号没有没有设置自动开奖,同步开奖结果失败.gameCode:{},issueNum:{}", gameCode, issueNum);
            return;
        }
        issue.syncLotteryNum(lotteryNum);
        Integer n = issueMapper.updateIssue(issue);
        //结算
        if (issue.getAutomaticSettlement()) {
            ThreadPoolUtils.getLotterySettlementPool().schedule(() ->{
                redisTemplate.opsForList().leftPush(Constant.当前开奖期号ID,issue.getId());
            },1, TimeUnit.SECONDS);
        }
    }

    public IssueVO getLatelyIssue(String gameCode) {
        IssueVO currentIssue = getCurrentIssue(gameCode);
        if(currentIssue == null){
            String now = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN);
            Issue latelyIssue = issueMapper.getTopIssueByEndTime(gameCode,now);
            return IssueVO.convertFor(latelyIssue);
        }
        Issue latelyIssue = issueMapper.getTopIssueByIssueNum(gameCode,currentIssue.getIssueNum());
        return IssueVO.convertFor(latelyIssue);
    }

    public IssueVO getCurrentIssue(String gameCode) {
        String now = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN);
        Issue currentIssue = issueMapper.getCurrentIssue(gameCode,now,now);
        return IssueVO.convertFor(currentIssue);
    }

    @Transactional
    public void generateIssue(DateTime currentDate) {
        List<IssueSetting> list = issueSettingService.getIssueSetting();
        for (IssueSetting issueSetting : list) {
            //默认生成5天的期次
            for (int i = 0; i < 5; i++) {
                Date lotteryDate = DateUtil.offset(DateUtil.beginOfDay(currentDate), DateField.DAY_OF_MONTH, i);
                List<Issue> issues = getIssueByGameCodeAndLotteryDate(issueSetting.getGame().getGameCode(), lotteryDate);
                if (!CollectionUtil.isEmpty(issues)) {
                    continue;
                }

                String lotteryDateFormat = DateUtil.format(lotteryDate, issueSetting.getDateFormat());
                Set<IssueGenerateRule> issueGenerateRules = issueSetting.getIssueGenerateRules();
                int count = 0;
                for (IssueGenerateRule issueGenerateRule : issueGenerateRules) {
                    Integer issueCount = issueGenerateRule.getIssueCount();
                    for (int j = 0; j < issueCount; j++) {
                        Long issueNum = Long.parseLong(lotteryDateFormat + String.format(issueSetting.getIssueFormat(), count + j + 1));
                        long issueNumInner = count + j + 1;
                        DateTime dateTime = DateUtil.parse(issueGenerateRule.getStartTime(), "hh:mm");
                        Date startTime = DateUtil.offset(lotteryDate, DateField.MINUTE,
                                dateTime.hour(true) * 60 + dateTime.minute() + j * issueGenerateRule.getTimeInterval());
                        Date endTime = DateUtil.offset(startTime, DateField.MINUTE,
                                issueGenerateRule.getTimeInterval());
                        Issue issue = new Issue();
                        issue.setId(IdUtils.getId());
                        issue.setGameCode(issueSetting.getGame().getGameCode());
                        issue.setLotteryDate(lotteryDate);
                        issue.setLotteryTime(endTime);
                        issue.setIssueNum(issueNum);
                        issue.setIssueNumInner(issueNumInner);
                        issue.setStartTime(startTime);
                        issue.setEndTime(endTime);
                        issue.setState(Constant.期号状态_未开奖);
                        issue.setAutomaticLottery(true);
                        issue.setAutomaticSettlement(true);
                        save(issue);
                        Date effectTime = DateUtil.offset(issue.getEndTime(), DateField.SECOND, 2);

                        XxlMqProducer.produce(new XxlMqMessage("SYNC_LOTTERY_NUM_" + issue.getGameCode(),
                                JSON.toJSONString(new SyncLotteryNumMsg(issue.getGameCode(), issue.getIssueNum(), 0)),
                                effectTime));
                    }
                    count += issueCount;
                }
            }
        }

    }

    public IssueVO getIssueByGameCodeAndIssueNum(String gameCode, Long issueNum) {
        return IssueVO.convertFor(issueMapper.getTopIssueByIssueNum(gameCode,issueNum));
    }
}
