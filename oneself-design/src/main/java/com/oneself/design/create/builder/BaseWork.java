package com.oneself.design.create.builder;

import java.math.BigDecimal;

/**
 * @Description:
 * @Title: BaseWork
 * @Author wen
 * @Date: 2022/9/10 19:16
 */
public class BaseWork extends BaseBuilder{

    private BaseUser baseUser;

    @Override
    BaseUser getBaseUser() {
        return this.baseUser;
    }

    @Override
    void getMoney() {
        baseUser.setMoney(new BigDecimal(10));
    }

    @Override
    void compute() {
        baseUser.setScore(new BigDecimal(10));
    }

    @Override
    void setLevel() {
        baseUser.setLevel(new BigDecimal(10));
    }
}
