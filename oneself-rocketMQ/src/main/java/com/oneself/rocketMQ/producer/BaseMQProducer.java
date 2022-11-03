package com.oneself.rocketMQ.producer;

import com.alibaba.fastjson.JSON;
import com.oneself.rocketMQ.factory.AbstractMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Title: BaseMQProducer
 * @Author wen
 * @Date: 2022/8/26 16:48
 */
@Component
public class BaseMQProducer extends AbstractMQProducer {


    private static String TOPIC="simple-message-topic";


    private static String  DESTINATION="simple-message-topic:sync-tags";

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    /**
     * 发送消息
     * @param message
     */
    @Override
    public void sendMessage(String message) {
        rocketMQTemplate.convertAndSend(TOPIC,message);
    }


    /**
     * 同步发送
     * @param id
     * @param message
     */
    @Override
    public void sendSyncMessage(String id, String message) {
        Message<String> strMessage = MessageBuilder.withPayload(message).setHeader(RocketMQHeaders.KEYS, id).build();
        SendResult result = rocketMQTemplate.syncSend(DESTINATION, strMessage);
    }


    /**
     * 异步发送
     * @param id
     * @param message
     */
    @Override
    public void sendAsyncMessage(String id, String message) {
        Message<String> strMessage = MessageBuilder.withPayload(message).setHeader(RocketMQHeaders.KEYS, id).build();
        rocketMQTemplate.asyncSend(DESTINATION, strMessage, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
                }
            }
            @Override
            public void onException(Throwable throwable) {
            }
        });
    }


    @Override
    public void sendOnewayMessage(String id, String message) {
        Message<String> strMessage = MessageBuilder.withPayload(message).setHeader(RocketMQHeaders.KEYS, id).build();
        rocketMQTemplate.sendOneWay("simple-message-topic:oneway-tags", strMessage);
    }


}
