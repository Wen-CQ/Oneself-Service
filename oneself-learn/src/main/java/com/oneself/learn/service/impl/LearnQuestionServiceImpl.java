package com.oneself.learn.service.impl;

import com.oneself.learn.entity.LearnQuestion;
import com.oneself.learn.mapper.LearnQuestionMapper;
import com.oneself.learn.service.LearnQuestionService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 题目表(LearnQuestion)表服务实现类
 *
 * @author makejava
 * @since 2022-08-09 23:20:14
 */
@Service("learnQuestionService")
public class LearnQuestionServiceImpl extends ServiceImpl<LearnQuestionMapper, LearnQuestion> implements LearnQuestionService {
    @Autowired
    private LearnQuestionMapper learnQuestionMapper;

    
}
