package com.me.lotteryapi.issue.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @program: lottery-study
 * @description:
 * @author:
 * @create: 2019-07-14 10:01
 */
@Service
@Validated
@Slf4j
public class IssueService {

    public void test(@NotBlank String gameCode){
      log.info("-----gameCode-------"+gameCode);
    }
}
