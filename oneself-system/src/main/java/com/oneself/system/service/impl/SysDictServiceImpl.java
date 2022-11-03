package com.oneself.system.service.impl;

import com.oneself.system.entity.SysDict;
import com.oneself.system.mapper.SysDictMapper;
import com.oneself.system.service.SysDictService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (SysDict)表服务实现类
 *
 * @author makejava
 * @since 2022-07-28 19:30:51
 */
@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {
    @Autowired
    private SysDictMapper sysDictMapper;

    
}
