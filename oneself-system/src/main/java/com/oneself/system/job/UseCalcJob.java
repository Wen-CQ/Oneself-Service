package com.oneself.system.job;

import com.oneself.calc.factory.AbstractJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description:
 * @Title: UseCalcJob
 * @Author wen
 * @Date: 2022/7/29 19:56
 */
@Component
public class UseCalcJob extends AbstractJob {

    private static final Logger log = LoggerFactory.getLogger(UseCalcJob.class);

    @Override
    protected void execute() {
        log.info("UseCalcJob time{}",new Date());
    }

    public UseCalcJob() {
        super.jobClass=UseCalcJob.class;
        super.jobGroup="USER";
        super.jobName="用户计算";
        super.corn="0,10,20,30,40,50 * * * * ?";
    }
}
