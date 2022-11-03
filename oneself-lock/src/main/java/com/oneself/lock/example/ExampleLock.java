package com.oneself.lock.example;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:
 * @Title: ExampleLock
 * @Author wen
 * @Date: 2022/8/28 14:44
 */
@Slf4j
public class ExampleLock {


    static ReentrantLock reentrantLock = new ReentrantLock();


    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    /**
     * 可重入锁:是指在同一个线程在调外层方法获取锁的时候，再进入内层方法会自动获取锁。
     * 对象锁或类锁内部有计数器，一个线程每获得一次锁，计数器 +1；解锁时，计数器 -1。
     * 有多少次加锁，就要对应多少次解锁，加锁与解锁成对出现。
     */
    //外层
    public void outer() {
        reentrantLock.lock();
        try {
            inner();
        } catch (Exception e) {
            log.error("outer error", e);
        } finally {
        reentrantLock.unlock();
        }
    }

    //内层
    public void inner() {
        reentrantLock.lock();
        try {

        } catch (Exception e) {
            log.error("inner error", e);
        } finally {
        reentrantLock.unlock();
        }
    }

    /**
     * 读写锁：如果对某个资源是读操作，那多个线程之间并不会相互影响，可以通过添加读锁实现共享。
     * 如果有修改动作，为了保证数据的并发安全，此时只能有一个线程获得锁，我们称之为 写锁。
     * 读读是共享的；而 读写、写读 、写写 则是互斥的。
     */
    public void readWriteLock(){

    }

    //读锁
    public void read(){

    }
    //写锁
    public void write(){

    }


}
