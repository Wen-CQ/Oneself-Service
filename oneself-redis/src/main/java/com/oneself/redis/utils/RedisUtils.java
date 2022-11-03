package com.oneself.redis.utils;

import com.alibaba.fastjson.JSONObject;
import com.oneself.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * @Description: Redis 工具类
 * @Title: RedisUtils
 * @Author wen
 * @Date: 2022/8/30 1:22
 */
@Slf4j
@Component
public class RedisUtils {

    @Autowired
    private RedisService redisService;


    /**
     * redis 加锁
     * @param key
     * @param value
     * @return
     */
    public Boolean getLock(String key, Object value){
        try {
            if (redisService.setNx(key, value)){
                return true;
            }
        }catch (Exception e){
            redisService.deleteObject(key);
            log.error("RedisUtils.getLock error",e);
            return false;
        }
        return false;
    }

    /**
     * redis 加锁
     * @param key
     * @param value
     * @param timeout
     * @param unit
     * @return
     */
    public Boolean getLock(String key, Object value,long timeout, TimeUnit unit){
        try {
            if (redisService.setNx(key, value,timeout,unit)){
                return true;
            }
        }catch (Exception e){
            redisService.deleteObject(key);
            log.error("RedisUtils.getLock error",e);
            return false;
        }
        return false;
    }


    /**
     * 往普通消息队列中添加对象
     * @param key
     * @param value
     * @return
     */
    public Boolean messageQueueAdd(String key,Object value){
        try {
            redisService.rightPush(key,value);
            return true;
        }catch (Exception e){
            log.error("RedisUtils.messageQueueAdd error",e);
            return false;
        }
    }

    /**
     * 往普通消息队列中取出对象
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T>T  messageQueuePop(String key,Class<T> clazz){
        try {
            Object value = redisService.leftPop(key);
            String s = JSONObject.toJSONString(value);
            return JSONObject.parseObject(s, clazz);
        }catch (Exception e){
            log.error("RedisUtils.messageQueueAdd error",e);
            return null;
        }
    }


}
