package com.oneself.calc.service.impl;

import com.oneself.calc.entity.SysJob;
import com.oneself.calc.mapper.SysJobMapper;
import com.oneself.calc.service.SysJobService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 定时任务调度表(SysJob)表服务实现类
 *
 * @author makejava
 * @since 2022-07-29 19:01:10
 */
@Service("sysJobService")
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements SysJobService {
    @Autowired
    private SysJobMapper sysJobMapper;

    
}
