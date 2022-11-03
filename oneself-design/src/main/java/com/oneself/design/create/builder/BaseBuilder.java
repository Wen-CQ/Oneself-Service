package com.oneself.design.create.builder;


/**
 * @Description:  抽象建造者
 * 将一个复杂的构建与其表示相分离，使得同样的构建过程可以创建不同的表示。Java 中的 StringBuilder。
 * @Title: BaseBuild
 * @Author wen
 * @Date: 2022/9/10 19:08
 */
public abstract class BaseBuilder {

    abstract BaseUser getBaseUser();

    abstract void getMoney();

    abstract void compute();

     abstract void setLevel();


}
