package com.oneself.common.utils;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:
 * @Title: UtilsMain
 * @Author wen
 * @Date: 2022/9/12 3:34
 */
public class UtilsMain {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>(64);
        map.put("String","String");
        map.put("Double","String");

        Map<String, Object> table = new Hashtable<>(64);
        table.put("String","String");
        table.put("Double","String");

        ReentrantLock reentrantLock = new ReentrantLock();

        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();



    }
}
