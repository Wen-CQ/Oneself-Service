package com.oneself.design.conduct.Observer;

/**
 * @Description:
 * @Title: Subject
 * @Author wen
 * @Date: 2022/9/11 5:13
 */
public interface Subject {

    /**
     * 增加观察者
     *
     * @param observer
     */
     void add(Observer observer);

    /**
     * 删除观察者
     * @param observer
     */
     void del(Observer observer);

    /**
     * 通知所有的观察者
     */
     void notifyObservers();

    /**
     * 自身的操作
     */
     void operation();

}
