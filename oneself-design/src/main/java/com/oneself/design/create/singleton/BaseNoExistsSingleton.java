package com.oneself.design.create.singleton;

/**
 * @Description:  单例模式
 * 保证一个类仅有一个实例，并提供一个访问它的全局访问点。
 * 懒汉式  有人要才创建对象
 * @Title: NoExistsSingleton
 * @Author wen
 * @Date: 2022/9/10 19:00
 */
public class BaseNoExistsSingleton {

        private static BaseNoExistsSingleton instance=null;
        private BaseNoExistsSingleton (){}
        public static synchronized BaseNoExistsSingleton getInstance() {
            if (instance == null) {
                instance = new BaseNoExistsSingleton();
            }
            return instance;
        }


}
