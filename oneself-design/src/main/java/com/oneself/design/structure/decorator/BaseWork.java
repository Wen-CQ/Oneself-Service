package com.oneself.design.structure.decorator;

import java.math.BigDecimal;

/**
 * @Description: 装饰器模式允
 * 装饰器模式允许你向一个现有的对象添加新的功能，同时又不改变其结构，
 * 它是作为现有的类的一个包装。这种模式创建了一个装饰类，
 * 用来包装原有的类，并在保持类方法签名完整性的前提下，提供了额外的功能。
 * @Title: BaseWork
 * @Author wen
 * @Date: 2022/9/11 4:12
 */
public class BaseWork extends BaseDecorator{

    @Override
    void create() {
        super.setMoney(new BigDecimal(10));
        super.setScore(new BigDecimal(10));
    }

}
