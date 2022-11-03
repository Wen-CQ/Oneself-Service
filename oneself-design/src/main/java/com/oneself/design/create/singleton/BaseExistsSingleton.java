package com.oneself.design.create.singleton;


/**
 * @Description:  单例模式
 * 保证一个类仅有一个实例，并提供一个访问它的全局访问点。
 * 饿汉式  对象早早的就创建好
 * @Title: ExistsSingleton
 * @Author wen
 * @Date: 2022/9/10 18:58
 */
public class BaseExistsSingleton {
    private BaseExistsSingleton() {}
    private static final BaseExistsSingleton single = new BaseExistsSingleton();
    //静态工厂方法
    public static BaseExistsSingleton getInstance() {
        return single;
    }
}
