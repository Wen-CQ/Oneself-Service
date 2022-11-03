package com.oneself.lock.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 不可重入锁
 * @Description:
 * @Title: NoReentrantLock
 * @Author wen
 * @Date: 2022/8/28 17:51
 */
public class NoReentrantLock implements Lock {


    /** 当前绑定的线程 */
    private Thread thread;


    @Override
    public void lock() {
        synchronized (this){
            //当已有线程拿到锁时
            while (thread!=null){
                //使当前线程等待
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //绑定当前线程
            this.thread=Thread.currentThread();
        }

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        synchronized (this){
            if (thread!=Thread.currentThread()){
                return;
            }
            //解绑线程
            thread=null;
            //唤醒所有线程
            notifyAll();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
