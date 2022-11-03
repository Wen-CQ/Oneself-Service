package com.oneself.learn.mapper;

import com.oneself.learn.entity.LearnCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 科目表(LearnCourse)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-09 23:19:29
 */

@Mapper
public interface LearnCourseMapper extends BaseMapper<LearnCourse> {

}

