package com.oneself.design.structure.decorator;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @Description:
 * @Title: BaseDecorator
 * @Author wen
 * @Date: 2022/9/11 4:09
 */
@Slf4j
public abstract class BaseDecorator {

    private BigDecimal money;

    private BigDecimal score;

    public BigDecimal getMoney(){
        log.info("compute");
        return this.money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    abstract void create();
}
