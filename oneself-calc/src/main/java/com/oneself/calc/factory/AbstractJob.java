package com.oneself.calc.factory;

import com.oneself.calc.constant.ScheduleConstants;
import com.oneself.calc.entity.SysJob;
import com.oneself.calc.exception.TaskException;
import com.oneself.calc.mapper.SysJobMapper;
import com.oneself.calc.util.ScheduleUtils;
import com.oneself.common.utils.SpringUtils;
import org.quartz.*;
import org.quartz.impl.RemoteMBeanScheduler;
import org.quartz.impl.RemoteScheduler;
import org.quartz.impl.StdScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Objects;

/**
 * @Description:
 * @Title: Job
 * @Author wen
 * @Date: 2022/7/29 3:07
 */

public abstract class AbstractJob implements Job {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SysJobMapper sysJobMapper;

    private static final Logger log = LoggerFactory.getLogger(AbstractJob.class);

    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    protected Class<? extends Job> jobClass ;

    protected String jobGroup;

    protected String jobName ;

    protected String corn ;

    @Override
    public void execute(JobExecutionContext jobExecutionContext)  {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        Long jobId = ScheduleUtils.getJobId(jobKey);
        log.info("execute jobKey{}",jobKey);
        SysJob sysJob = SpringUtils.getBean(SysJobMapper.class).selectById(jobId);
        try {
            if (ScheduleConstants.Status.PAUSE.getValue().equals(sysJob.getStatus())){
               SpringUtils.getBean("scheduler",Scheduler.class).pauseJob(jobKey);
                return;
            }
            before(jobExecutionContext,sysJob);
            this.execute();
            after(jobExecutionContext,sysJob,null);
        }catch (Exception e){
            log.error("execute error",e);
            after(jobExecutionContext, sysJob, e);
        }
    }

    /**
     * 调用方法
     */
    protected abstract void execute();


    /**
     * 创建任务
     */
    @PostConstruct
    protected  void createJob(){
        try {

            SysJob sysJob = sysJobMapper.selectByJobName(jobName);
            if (Objects.isNull(sysJob)){
                sysJob = new SysJob();
                SysJob entity = new SysJob();
                entity.setJobName(jobName);
                entity.setJobGroup(jobGroup);
                entity.setCronExpression(corn);
                entity.setInvokeTarget(jobClass.getSimpleName());
                entity.setMisfirePolicy(ScheduleConstants.MISFIRE_DEFAULT);
                sysJobMapper.insert(entity);
                BeanUtils.copyProperties(entity,sysJob);
            }

            JobKey jobKey = ScheduleUtils.getJobKey(sysJob.getJobId(), jobGroup);
            TriggerKey triggerKey = TriggerKey.triggerKey(ScheduleConstants.TASK_CLASS_NAME + sysJob.getJobId(), jobGroup);

            JobDetail jobDetail = JobBuilder.newJob(this.jobClass).withIdentity(jobKey).build();
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(sysJob.getCronExpression());
            cronScheduleBuilder = handleCronScheduleMisfirePolicy(sysJob, cronScheduleBuilder);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();

            if (scheduler.checkExists(jobKey)){
                scheduler.deleteJob(jobKey);
            }
            if (ScheduleConstants.Status.PAUSE.getValue().equals(sysJob.getStatus())){
                scheduler.pauseJob(jobKey);
            }

            scheduler.scheduleJob(jobDetail, trigger);

        }catch (Exception e){
           log.info("createJob error jobName{}",jobName);
           log.error("createJob error",e);
        }

    }


    /**
     * 执行前
     *
     * @param context 工作执行上下文对象
     * @param sysJob 系统计划任务
     */
    protected void before(JobExecutionContext context, SysJob sysJob)
    {
        threadLocal.set(new Date());
    }

    /**
     * 执行后
     *
     * @param context 工作执行上下文对象
     * @param sysJob 系统计划任务
     */
    protected void after(JobExecutionContext context, SysJob sysJob, Exception e)
    {
        Date startTime = threadLocal.get();
        threadLocal.remove();
        Integer times = sysJob.getTimes();
        sysJob.setStartDate(startTime);
        sysJob.setEndDate(new Date());
        sysJob.setTimes(times.intValue()+1);
        SpringUtils.getBean(SysJobMapper.class).updateById(sysJob);

    }


    /**
     * 设置定时任务策略
     */
    public static CronScheduleBuilder handleCronScheduleMisfirePolicy(SysJob job, CronScheduleBuilder cb)
            throws TaskException
    {
        switch (job.getMisfirePolicy())
        {
            case ScheduleConstants.MISFIRE_DEFAULT:
                return cb;
            case ScheduleConstants.MISFIRE_IGNORE_MISFIRES:
                return cb.withMisfireHandlingInstructionIgnoreMisfires();
            case ScheduleConstants.MISFIRE_FIRE_AND_PROCEED:
                return cb.withMisfireHandlingInstructionFireAndProceed();
            case ScheduleConstants.MISFIRE_DO_NOTHING:
                return cb.withMisfireHandlingInstructionDoNothing();
            default:
                throw new TaskException("The task misfire policy '" + job.getMisfirePolicy()
                        + "' cannot be used in cron schedule tasks", TaskException.Code.CONFIG_ERROR);
        }
    }

}
