package com.oneself.rocketMQ.factory;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Title: SimpleProducerFactory
 * @Author wen
 * @Date: 2022/8/26 16:29
 */
public abstract class AbstractMQProducer implements SimpleMessageFactory {


    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void sendSyncMessage(String id, String message) {

    }

    @Override
    public void sendAsyncMessage(String id, String message) {

    }

    @Override
    public void sendOnewayMessage(String id, String message) {

    }
}
