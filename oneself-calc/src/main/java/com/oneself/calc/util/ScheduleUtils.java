package com.oneself.calc.util;

import com.oneself.calc.constant.ScheduleConstants;
import org.quartz.JobKey;

/**
 * @Description:
 * @Title: ScheduleUtils
 * @Author wen
 * @Date: 2022/7/29 19:12
 */
public class ScheduleUtils {


    public static JobKey getJobKey(Long jobId, String jobGroup)
    {
        return JobKey.jobKey(ScheduleConstants.TASK_CLASS_NAME + jobId, jobGroup);
    }

    public static Long getJobId(JobKey jobKey){
        String id = jobKey.getName().replaceAll(ScheduleConstants.TASK_CLASS_NAME, "");
        return Long.valueOf(id);
    }

}
