package com.me.lotterytask.issuetask;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.me.lotteryapi.constants.Constant;
import com.me.lotteryapi.issue.entity.SyncLotteryNumMsg;
import com.me.lotteryapi.issue.service.CqsscService;
import com.me.lotteryapi.issue.service.IssueService;
import com.me.lotteryapi.issue.vo.IssueVO;
import com.xxl.mq.client.consumer.IMqConsumer;
import com.xxl.mq.client.consumer.MqResult;
import com.xxl.mq.client.consumer.annotation.MqConsumer;
import com.xxl.mq.client.message.XxlMqMessage;
import com.xxl.mq.client.producer.XxlMqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: lottery-study
 * @description:
 * @author:
 * @create: 2019-08-01 11:09
 */
@MqConsumer(topic = SyncCqsscLotteryNumTask.TOPIC)
@Component
@Slf4j
public class SyncCqsscLotteryNumTask implements IMqConsumer {

    public static final String TOPIC = "SYNC_LOTTERY_NUM_CQSSC";

    @Autowired
    private IssueService issueService;

    @Autowired
    private CqsscService cqsscService;

    private static final List<Integer> RETRY_LEVEL = Arrays.asList(65, 25, 18, 18, 16, 16, 14, 14, 10, 10, 25, 25, 18,
            18);

    // {"gameCode":"YNSSC","issueNum":20190714021,"retries":1}
    @Override
    public MqResult consume(String data) throws Exception {
        SyncLotteryNumMsg msg = JSON.parseObject(data, SyncLotteryNumMsg.class);
        IssueVO latelyIssue = issueService.getLatelyIssue(msg.getGameCode());
        if(msg.getIssueNum().compareTo(latelyIssue.getIssueNum()) != 0){
            return MqResult.SUCCESS;
        }
        //是否同步开奖号码
        Boolean syncSuccessFlag = false;
        try {
            log.info("执行同步重庆时时彩开奖号码定时任务start");
            cqsscService.syncLotteryNum();
            IssueVO issue = issueService.getIssueByGameCodeAndIssueNum(msg.getGameCode(), msg.getIssueNum());
            syncSuccessFlag = !Constant.期号状态_未开奖.equals(issue.getState());
            log.info("执行同步重庆时时彩开奖号码定时任务end");
        }catch (Exception e){
            log.error("执行同步重庆时时彩开奖号码定时任务发生异常",e);
        }
        if(!syncSuccessFlag){
            if(msg.getRetries() < RETRY_LEVEL.size()){
                Date effectTime = DateUtil.offset(new Date(), DateField.SECOND,RETRY_LEVEL.get(msg.getRetries()));
                msg.setRetries(msg.getRetries()+1);
                XxlMqProducer.produce(new XxlMqMessage(TOPIC,JSON.toJSONString(msg),effectTime));
            }
        }
        return MqResult.SUCCESS;
    }
}
