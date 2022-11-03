package com.oneself.redis.aop;

import com.oneself.redis.annotation.RedissonLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @Description: redisson 分布式锁切面处理
 * @Title: RedissonLockAspect
 * @Author wen
 * @Date: 2022/8/30 1:44
 */
@Aspect
@Component
@Slf4j
public class RedissonLockHandler {


    @Autowired
    private RedissonClient redissonClient;

    private ExpressionParser parser = new SpelExpressionParser();

    private LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    @Pointcut("@annotation(com.oneself.redis.annotation.RedissonLock)")
    public void lockPoint(){
    }


    @Around("lockPoint()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        RedissonLock redissonLock = method.getAnnotation(RedissonLock.class);
        String key = redissonLock.value();
        Object[] args = pjp.getArgs();
        //key = parse(key, method, args);
        RLock lock = getLock(key, redissonLock);
        if(!lock.tryLock(redissonLock.waitTime(), redissonLock.leaseTime(), redissonLock.unit())) {
            log.debug("get lock failed [{}]", key);
            log.info(String.format("get lock failed [%s]", LocalDateTime.now()));
            return null;
        }
        //得到锁,执行方法，释放锁
        log.debug("get lock success [{}]", key);
        try {
            log.info(String.format("获取锁%s执行时间为:%s", key, LocalDateTime.now()));
            return pjp.proceed();

        } catch (Exception e) {
            log.error("execute locked method occured an exception", e);
        } finally {
            lock.unlock();
            log.debug("release lock [{}]", key);
        }
        return null;
    }


    /**
     * 解析spring EL表达式
     * @param key
     * @param method
     * @param args
     * @return
     */
    private String parse(String key, Method method, Object[] args) {
        String[] params = discoverer.getParameterNames(method);
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < params.length; i ++) {
            context.setVariable(params[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }

    private RLock getLock(String key, RedissonLock redissonLock) {
        switch (redissonLock.lockType()) {
            case REENTRANT_LOCK:
                return redissonClient.getLock(key);
            default:
                throw new RuntimeException("do not support lock type:" + redissonLock.lockType().name());
        }
    }

}
