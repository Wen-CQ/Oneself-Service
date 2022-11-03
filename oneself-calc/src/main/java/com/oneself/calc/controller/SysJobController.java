package com.oneself.calc.controller;




import com.oneself.calc.entity.SysJob;
import com.oneself.calc.service.SysJobService;
import com.oneself.calc.util.ScheduleUtils;
import com.oneself.common.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 定时任务调度表(SysJob)表控制层
 *
 * @author makejava
 * @since 2022-07-29 19:01:09
 */
@Slf4j
@RestController
@RequestMapping("sysJob")
public class SysJobController{
    /**
     * 服务对象
     */
    @Autowired
    private SysJobService sysJobService;

    @Autowired
    private Scheduler scheduler;

    @RequestMapping("/list")
    public Result<List<SysJob>> list(){
        List<SysJob> list = sysJobService.list();
        return Result.ok(list);
    }

    /**
     * 暂停任务
     * @param jobId
     * @return
     */
    @RequestMapping("/pauseJob/{jobId}")
    public Result<String> pauseJob(@PathVariable("jobId") Long jobId){
        SysJob sysJob = sysJobService.getById(jobId);
        sysJob.setStatus("1");
        sysJobService.updateById(sysJob);
        return Result.ok();
    }


    /**
     * 恢复任务
     * @param jobId
     * @return
     */
    @RequestMapping("/resumeJob/{jobId}")
    public Result<String> resumeJob(@PathVariable("jobId") Long jobId){
        try {
            SysJob sysJob = sysJobService.getById(jobId);
            sysJob.setStatus("0");
            sysJobService.updateById(sysJob);
            JobKey jobKey = ScheduleUtils.getJobKey(sysJob.getJobId(), sysJob.getJobGroup());
            scheduler.resumeJob(jobKey);
        }catch (Exception e){
            log.error("resumeJob error",e);
        }
        return Result.ok();
    }

}

