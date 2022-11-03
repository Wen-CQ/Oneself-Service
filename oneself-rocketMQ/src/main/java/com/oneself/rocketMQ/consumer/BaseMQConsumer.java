package com.oneself.rocketMQ.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * @Description:
 * @Title: BaseMQConsumer
 * @Author wen
 * @Date: 2022/8/26 16:55
 */
@Component
@RocketMQMessageListener(topic = "simple-message-topic", consumerGroup = "${rocketmq.consumer.group}")
public class BaseMQConsumer implements RocketMQListener<String> {


    @Override
    public void onMessage(String s) {
        LinkedList<Object> objects = new LinkedList<>();
    }
}
