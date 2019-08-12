package com.me.lotteryapi.common.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: lottery-study
 * @description:
 * @author:
 * @create: 2019-08-03 17:59
 */
public class ThreadPoolUtils {

    /**
     * 核心线程数，会一直存活，即使没有任务，线程池也会维护线程的最少数量
     */
    private static final int SIZE_CORE_POOL = 5;
    /**
     * 线程池维护线程的最大数量
     */
    private static final int SIZE_MAX_POOL = 10;
    /**
     * 线程池维护线程所允许的空闲时间
     */
    private static final long ALIVE_TIME = 2000;

    private static ThreadPoolExecutor syncLotteryThreadPool = new ThreadPoolExecutor(SIZE_CORE_POOL, SIZE_MAX_POOL, ALIVE_TIME, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(100), new ThreadPoolExecutor.CallerRunsPolicy());

    static {
        syncLotteryThreadPool.prestartAllCoreThreads();
    }

    /**
     * 开奖结算线程池
     */
    private static ScheduledThreadPoolExecutor lotterySettlementPool = new ScheduledThreadPoolExecutor(4);

    public static ThreadPoolExecutor getSyncLotteryThreadPool() {
        return syncLotteryThreadPool;
    }

    public static ScheduledThreadPoolExecutor getLotterySettlementPool() {
        return lotterySettlementPool;
    }

}
