package com.oneself.learn.controller;




import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oneself.common.domain.Result;
import com.oneself.learn.entity.LearnCourse;
import com.oneself.learn.service.LearnCourseService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


/**
 * 科目表(LearnCourse)表控制层
 *
 * @author makejava
 * @since 2022-08-09 23:19:30
 */
@RestController
@RequestMapping("learnCourse")
public class LearnCourseController{
    /**
     * 服务对象
     */
    @Autowired
    private LearnCourseService learnCourseService;


    @Cacheable(value = "banner",key ="'learnCourse:list'")
    @RequestMapping("/list")
    public Result<Map<String,Object>> list(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize){
        Map<String, Object> data = new HashMap<>();
        Page<LearnCourse> pages = new Page<>(page, pageSize);
        Page<LearnCourse> pageData = learnCourseService.page(pages);
        data.put("list", pageData.getRecords());
        data.put("total", pageData.getTotal());
        data.put("page", page);
        data.put("pageSize", pageSize);
        return Result.ok(data);
    }


    @RequestMapping("/add")
    public Result add(@RequestBody LearnCourse entity){
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        learnCourseService.save(entity);
        return Result.ok();
    }


    @RequestMapping("/randLearn")
    @ResponseBody
    public Result<List<LearnCourse>> randLearn(){
        List<LearnCourse> list = learnCourseService.list();
        if (CollectionUtils.isEmpty(list)){
            return Result.ok();
        }
        int total = list.size();
        Random random = new Random();
        int index = random.nextInt(total);
        LearnCourse learnCourse = list.get(index);
        List<LearnCourse> data = new ArrayList<>();
        data.add(learnCourse);
        return Result.ok(data);
    }

}

