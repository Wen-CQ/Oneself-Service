package com.oneself.transaction.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @Description: 编程式事务
 * @Title: ProgrammaticTransactions
 * @Author wen
 * @Date: 2022/8/30 2:40
 */
@Component
public class ProgrammaticTransactions  {

    @Autowired
    private TransactionTemplate transactionTemplate;

    public void transactions() {
        // 开始编程式事务1
        Integer i = transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus transactionStatus) {
                // 先改为处理中状态
                // 返回状态
                return 0;
            }
        });
        if (i == null || i == 0) {
            throw new RuntimeException("处理中失败！");
        }
        // 模拟分布式接口调用
        // 开始编程式事务2 （函数式写法）
        Integer count = transactionTemplate.execute((transactionStatus) -> 0);
        if (count == null || count == 0) {
            throw new RuntimeException("更新失败！");
        }
    }



}
