package com.oneself.redis.controller;

import com.oneself.common.domain.Result;
import com.oneself.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Title: RedisController
 * @Author wen
 * @Date: 2022/9/4 16:36
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    RedisService redisService;


    @RequestMapping("/get")
    public Result<Map<String,Object>> get(String key){
        Map<String, Object> data = new HashMap<>();
        Object cacheObject = redisService.getCacheObject(key);
        data.put("key",key);
        data.put("object",cacheObject);
        return Result.ok(data);
    }

    @RequestMapping("/del")
    public Result<Map<String,Object>> del(String key){
        Map<String, Object> data = new HashMap<>();
        boolean isDelete = redisService.deleteObject(key);
        data.put("key",key);
        data.put("object",isDelete);
        return Result.ok(data);
    }

    @RequestMapping("/keys")
    public Result<Map<String,Object>> keys(String pattern){
        Map<String, Object> data = new HashMap<>();
        Collection<String> keys = redisService.keys(pattern);
        data.put("pattern",pattern);
        data.put("object",keys);
        return Result.ok(data);
    }

}
