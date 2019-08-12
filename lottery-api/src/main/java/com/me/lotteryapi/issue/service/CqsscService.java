package com.me.lotteryapi.issue.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.me.lotteryapi.common.utils.ThreadPoolUtils;
import com.me.lotteryapi.constants.Constant;
import com.me.lotteryapi.issue.vo.IssueVO;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: lottery-study
 * @description: 重庆时时彩service
 * @author:
 * @create: 2019-08-05 13:27
 */
@Service
@Slf4j
public class CqsscService {

    @Autowired
    private IssueService issueService;


    /**
     * 同步当前开奖号码
     */
    public void syncLotteryNum() {
        IssueVO latestWithInterface = getLatestLotteryResultWithApi();
        if (latestWithInterface == null) {
            return;
        }
        issueService.syncLotteryNum(Constant.游戏_重庆时时彩, latestWithInterface.getIssueNum(), latestWithInterface.getLotteryNum());
    }

    private IssueVO getLatestLotteryResultWithApi() {
        List<IssueVO> issues = new ArrayList<>();
        CountDownLatch countlatch = new CountDownLatch(2);
        List<Future<IssueVO>> futures = new ArrayList<>();
        futures.add(ThreadPoolUtils.getSyncLotteryThreadPool().submit(() -> {
            return getLatestLotteryIssueWithAiCai();
        }));
        futures.add(ThreadPoolUtils.getSyncLotteryThreadPool().submit(() -> {
            return getLatestLotteryResultWithOpenCai();
        }));
        for (Future<IssueVO> future : futures) {
            try {
                IssueVO issueVO = future.get(3, TimeUnit.SECONDS);
                issues.add(issueVO);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                log.error("异步查询开奖future接口出现错误", e);
            }
            countlatch.countDown();
        }
        try {
            countlatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        issues.sort(new Comparator<IssueVO>() {
            @Override
            public int compare(IssueVO o1, IssueVO o2) {
                if (o1 == null) {
                    return -1;
                }
                if (o2 == null) {
                    return -1;
                }
                if (o1 != null && o2 != null) {
                    return o2.getIssueNum().compareTo(o1.getIssueNum());
                }
                return 0;
            }
        });
        return issues.isEmpty() ? null : issues.get(0);
    }

    private IssueVO getLatestLotteryResultWithOpenCai() {
        String result = HttpUtil.get("http://f.apiplus.net/cqssc.json");
        log.info("接口返回结果:{}", result);
        JSONObject resultJsonObject = JSON.parseObject(result);
        JSONArray jsonArray = resultJsonObject.getJSONArray("data");
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        long issueNum = Long.parseLong(jsonObject.getString("expect"));
        String lotteryNum = jsonObject.getString("opencode");
        IssueVO lotteryResult = IssueVO.builder().issueNum(issueNum).lotteryDate(null).lotteryNum(lotteryNum)
                .build();
        return lotteryResult;
    }

    private IssueVO getLatestLotteryIssueWithAiCai() {
        IssueVO lotteryResult = null;
        try {
            String url = "https://kaijiang.aicai.com/cqssc/";
            Document document = Jsoup.connect(url).get();
            Element element = document.getElementById("jq_body_kc_result").child(0);
            String issueText = element.child(0).text();
            long issueNum = Long.parseLong(issueText.substring(0, issueText.length() - 1).replace("-", ""));
            String lotteryNum = element.child(2).text().replace("|", ",");
            lotteryResult = IssueVO.builder().issueNum(issueNum).lotteryDate(null).lotteryNum(lotteryNum).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lotteryResult;
    }

    public static void main(String[] argus) {
        List<IssueVO> issues = new ArrayList<>();
        IssueVO issueVO1 = IssueVO.builder().issueNum(123L).build();
        IssueVO issueVO2 = IssueVO.builder().issueNum(124L).build();
        issues.add(issueVO1);
        issues.add(issueVO2);
        System.out.println(issues.size());

        System.out.println(issues);
    }

}
