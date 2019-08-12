package com.me.lotterytask;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.me.lotteryapi.issue.entity.SyncLotteryNumMsg;
import com.me.lotteryapi.issue.service.IssueService;
import com.xxl.mq.client.message.XxlMqMessage;
import com.xxl.mq.client.producer.XxlMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @program: lottery-study
 * @description:
 * @author:
 * @create: 2019-07-15 17:56
 */
@RestController
public class TestController {
    @Autowired
    IssueService issueService;
    @PostMapping("/test")
    public String test(){
        XxlMqProducer.produce(new XxlMqMessage("SYNC_LOTTERY_NUM_CQSSC",
                JSON.toJSONString(new SyncLotteryNumMsg("CQSSC", 20190613001L, 0)),
                2));
        return "test";
    }
}
