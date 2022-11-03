package com.oneself.system.mapper;

import com.oneself.system.entity.SysDict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;


/**
 * (SysDict)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-28 19:30:48
 */
@CacheNamespace
@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {

}

