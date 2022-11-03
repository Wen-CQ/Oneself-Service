package com.oneself.design.create.builder;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Title: BaseUser
 * @Author wen
 * @Date: 2022/9/10 19:21
 */
@Data
public class BaseUser {

    private BigDecimal score;

    private BigDecimal level;

    private BigDecimal money;
}
