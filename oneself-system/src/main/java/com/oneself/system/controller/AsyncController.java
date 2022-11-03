package com.oneself.system.controller;

import com.oneself.common.domain.Result;
import com.oneself.system.service.TaskExecutePoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Title: AsyncController
 * @Author wen
 * @Date: 2022/8/21 0:41
 */
@Slf4j
@RestController
@RequestMapping("async")
public class AsyncController {

    @Autowired
    private TaskExecutePoolService taskExecutePoolService;

    @RequestMapping("/test")
    public Result<String> test(){
        taskExecutePoolService.asyncTest();
        return Result.ok();
    }


}
