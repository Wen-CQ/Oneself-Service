package com.oneself.thread.config;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:  可监控线程池
 * @Title: MonitorThreadPool
 * @Author wen
 * @Date: 2022/8/28 18:14
 */
@Slf4j
public class MonitorThreadPool extends ThreadPoolExecutor {

    public MonitorThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        monitor();
    }


    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        monitor();
    }


    @Override
    protected void terminated() {
        monitor();
    }

    /**
     * 监控线程池情况
     */
    public void monitor(){
     log.info("正在工作的线程数：{}",getActiveCount());
     log.info("当前存在的线程数：{}",getPoolSize());
     log.info("历史最大的线程数：{}",getLargestPoolSize());
     log.info("已提交的任务数：{}",getTaskCount());
     log.info("已完成的任务数：{}",getCompletedTaskCount());
     log.info("队列中的任务数：{}",getQueue().size());
    }
}
