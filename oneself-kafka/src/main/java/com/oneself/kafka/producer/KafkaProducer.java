package com.oneself.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @Description:
 * @Title: KafkaProducer
 * @Author wen
 * @Date: 2022/8/26 15:34
 */

@Slf4j
@Component
public class KafkaProducer {

    private static String  TOPIC = "topic";

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    /**
     * 发送
     * @param message
     */
    public void  send(String message){
        kafkaTemplate.send(TOPIC,message);
    }


    /**
     * 同步发送
     * @param message
     */
    public void  sendSyncMessage(String message){
        try {
            SendResult<String, Object> sendResult = kafkaTemplate.send(TOPIC, message).get();
            log.info("sendSyncMessage sendResult{}",sendResult);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 异步发送
     * ProducerRecord、RecordMetadata包含了返回的结果信息
     * @param message
     */
    public void sendAsyncMessage(String message){
        try {
            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC, message);

            future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

                /**
                 * 失败时
                 * @param throwable
                 */
                @Override
                public void onFailure(Throwable throwable) {

                }

                /**
                 * 成功后
                 * @param stringObjectSendResult
                 */
                @Override
                public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                   log.info("ProducerRecord",stringObjectSendResult.getProducerRecord());
                   log.info("RecordMetadata",stringObjectSendResult.getRecordMetadata());
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
