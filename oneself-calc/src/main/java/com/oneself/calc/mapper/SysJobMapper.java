package com.oneself.calc.mapper;

import com.oneself.calc.entity.SysJob;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * 定时任务调度表(SysJob)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-29 19:01:09
 */
@Mapper
public interface SysJobMapper extends BaseMapper<SysJob> {


    @Select("select * from `sys_job` where job_name = #{jobName} limit 1")
    SysJob selectByJobName(@Param("jobName") String jobName);
}

