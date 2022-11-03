package com.oneself.learn.mapper;

import com.oneself.learn.entity.LearnQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 题目表(LearnQuestion)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-09 23:20:14
 */
@Mapper
public interface LearnQuestionMapper extends BaseMapper<LearnQuestion> {

}

