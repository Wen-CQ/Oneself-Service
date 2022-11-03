package com.oneself.redis.annotation;

import com.oneself.common.enums.LockTypeEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @Description: redis 自定义加锁注解
 * @Title: RedissonLock
 * @Author wen
 * @Date: 2022/8/30 1:39
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedissonLock {

    /** 锁的资源，key。支持spring El表达式*/
    @AliasFor("key")
    String value() default "'default'";

    @AliasFor("value")
    String key() default "'default'";

    /** 锁类型*/
    LockTypeEnum lockType() default LockTypeEnum.REENTRANT_LOCK;

    /** 获取锁等待时间，默认10秒*/
    long waitTime() default 10000L;

    /** 锁自动释放时间，默认10秒*/
    long leaseTime() default 10000L;

    /** 时间单位（获取锁等待时间和持锁时间都用此单位）*/
    TimeUnit unit() default TimeUnit.MILLISECONDS;

}
