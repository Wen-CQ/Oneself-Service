package com.oneself.learn.controller;




import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oneself.common.domain.Result;
import com.oneself.common.utils.NumberUtils;
import com.oneself.learn.entity.LearnCourse;
import com.oneself.learn.entity.LearnQuestion;
import com.oneself.learn.service.LearnQuestionService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


/**
 * 题目表(LearnQuestion)表控制层
 *
 * @author makejava
 * @since 2022-08-09 23:20:14
 */
@RestController
@RequestMapping("learnQuestion")
public class LearnQuestionController{
    /**
     * 服务对象
     */
    @Autowired
    private LearnQuestionService learnQuestionService;


    @RequestMapping("/list")
    public Result<Map<String, Object>> list(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize){
        Map<String, Object> data = new HashMap<>();
        Page<LearnQuestion> pages = new Page<>(page, pageSize);
        Page<LearnQuestion> pageData = learnQuestionService.page(pages);
        data.put("list", pageData.getRecords());
        data.put("total", pageData.getTotal());
        data.put("page", page);
        data.put("pageSize", pageSize);
        return Result.ok(data);
    }


    @RequestMapping("/add")
    public Result add(@RequestBody LearnQuestion entity){
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        learnQuestionService.save(entity);
        return Result.ok();
    }

    @RequestMapping("/randLearnQuestion")
    @ResponseBody
    public Result<List<LearnQuestion>> randLearnQuestion(Long courseId){

        HashMap<String, Object> map = new HashMap<>();
        map.put("course_id",courseId);
        List<LearnQuestion> list = learnQuestionService.listByMap(map);
        if (CollectionUtils.isEmpty(list)){
            return Result.ok();
        }
        List<LearnQuestion> data = new ArrayList<>();
        List<Integer> indexList = NumberUtils.randNumberArray(list.size()>5?5:list.size(), list.size());
        indexList.stream().forEach(index -> {
            LearnQuestion learnQuestion =   list.get(index);
            data.add(learnQuestion);
        });
        return Result.ok(data);
    }

}

