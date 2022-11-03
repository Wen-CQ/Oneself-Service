package com.oneself.learn.service.impl;

import com.oneself.learn.entity.LearnCourse;
import com.oneself.learn.mapper.LearnCourseMapper;
import com.oneself.learn.service.LearnCourseService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 科目表(LearnCourse)表服务实现类
 *
 * @author makejava
 * @since 2022-08-09 23:19:32
 */
@Service("learnCourseService")
public class LearnCourseServiceImpl extends ServiceImpl<LearnCourseMapper, LearnCourse> implements LearnCourseService {
    @Autowired
    private LearnCourseMapper learnCourseMapper;

    
}
