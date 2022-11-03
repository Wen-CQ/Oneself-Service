package com.oneself.design.conduct.Observer;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @Description: 定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，
 * 所有依赖于它的对象都得到通知并被自动更新。
 * @Title: AbstractSubject
 * @Author wen
 * @Date: 2022/9/11 5:14
 */
public abstract class AbstractSubject implements Subject {


    private Vector<Observer> vector = new Vector<Observer>();


    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }


    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }


    @Override
    public void notifyObservers() {
        Enumeration<Observer> enumeration = vector.elements();
        while (enumeration.hasMoreElements()) {
            enumeration.nextElement().update();
        }
    }

    @Override
    public void operation() {

    }


}
