package com.me.lotterytask.issuetask;

import com.alibaba.fastjson.JSON;
import com.me.lotteryapi.issue.entity.SyncLotteryNumMsg;
import com.me.lotteryapi.issue.service.IssueService;
import com.me.lotteryapi.issue.vo.IssueVO;
import com.xxl.mq.client.consumer.IMqConsumer;
import com.xxl.mq.client.consumer.MqResult;
import com.xxl.mq.client.consumer.annotation.MqConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Arrays;
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

        Boolean syncSuccessFlag = false;
        log.info("执行同步重庆时时彩开奖号码定时任务start");

        return null;
    }
}
