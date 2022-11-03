package com.oneself.system.service.impl;

import com.oneself.system.service.TaskExecutePoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Title: TaskExecutePoolServiceImpl
 * @Author wen
 * @Date: 2022/8/21 1:09
 */
@Slf4j
@Service("taskExecutePoolService")
public class TaskExecutePoolServiceImpl implements TaskExecutePoolService {


    @Async("taskExecutor")
    @Override
    public void asyncTest() {
        log.info("taskExecutor thread{}",Thread.currentThread().getName());
    }
}
