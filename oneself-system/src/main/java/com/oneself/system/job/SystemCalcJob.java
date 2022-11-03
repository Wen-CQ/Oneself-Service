package com.oneself.system.job;

import com.oneself.calc.factory.AbstractJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description:
 * @Title: SystemCalcJob
 * @Author wen
 * @Date: 2022/7/30 1:40
 */
@Component
public class SystemCalcJob extends AbstractJob {

    private static final Logger log = LoggerFactory.getLogger(UseCalcJob.class);

    @Override
    protected void execute() {
        log.info("SystemCalcJob time{}",new Date());
    }

    public SystemCalcJob() {
        super.jobClass=SystemCalcJob.class;
        super.jobGroup="SYSTEM";
        super.jobName="系统计算";
        super.corn="0,10,20,30,40,50 * * * * ?";
    }
}
