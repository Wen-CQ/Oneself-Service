package com.oneself.design.create.builder;

/**
 * @Description:
 * @Title: BaseDirector 指挥者
 * @Author wen
 * @Date: 2022/9/10 19:19
 */
public class BaseDirector {

    public static BaseUser build(BaseBuilder builder){
        builder.setLevel();
        builder.compute();
        builder.getMoney();
        return builder.getBaseUser();
    }

}
