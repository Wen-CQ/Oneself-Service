package com.oneself.transaction.example;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Description:  声明式事务（Spring Boot 1.0 需要开启 @EnableTransactionManagement Spring Boot 2.0  默认开启）
 * @Title: TransactionExample
 * @Author wen
 * @Date: 2022/8/30 0:42
 */
@Component
public class StatementTransaction {


    /**
     * Default(默认数据库隔离级别 Mysql(Repeatable read) Oracle(Read committed))
     * REQUIRED(必须的) ：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
     */
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void  defaultSava(){

    }
    
    
    /**
     * Read uncommitted(读未提交) : 最低的隔离级别，允许读取尚未提交的数据变更，可能会导致脏读、幻读或不可重复读
     * SUPPORTS(支持) ：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED,propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    public void  readUncommittedSava(){

    }


    /**
     * Read committed(读提交):允许读取并发事务已经提交的数据，可以阻止脏读，但是幻读或不可重复读仍有可能发生
     * MANDATORY(强制) ：如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
     */
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.MANDATORY,rollbackFor = Exception.class)
    public void  readCommittedSava(){

    }

    /**
     * Repeatable read(可重复读取):对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，可以阻止脏读和不可重复读，但幻读仍有可能发生。
     * REQUIRES_NEW(需要新的) ：创建一个新的事务，如果当前存在事务，则把当前事务挂起。
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void  repeatableReadSava(){

    }



    /**
     * Serializable(串行化):最高的隔离级别，完全服从ACID的隔离级别。所有的事务依次逐个执行，
     * 这样事务之间就完全不可能产生干扰，也就是说，该级别可以防止脏读、不可重复读以及幻读。
     * 但是这将严重影响程序的性能。通常情况下也不会用到该级别。
     * NOT_SUPPORTED(不支持) ：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
     * NEVER(绝不) ：以非事务方式运行，如果当前存在事务，则抛出异常。
     * NESTED(嵌套) ：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，
     */
    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.NOT_SUPPORTED,rollbackFor = Exception.class)
  //@Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.NEVER,rollbackFor = Exception.class)
  //@Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.NESTED,rollbackFor = Exception.class)
    public void serializableSava(){

    }



}
