package com.oneself.kafka.Listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @Description:
 * @Title: KafkaConsumerListener
 * @Author wen
 * @Date: 2022/7/30 15:19
 */
@Slf4j
public class KafkaConsumerListener {

    @KafkaListener(groupId="xxx" ,topics = "xxx")
    void listener(ConsumerRecord<String, String> data){

        log.info("消费者线程ThreadName{},消息来自KafkaTopic{},分区partition{},委托时间timestamp{},消息内容value{}",
                Thread.currentThread().getName(),data.topic(),data.partition(),data.timestamp(),data.value());
    }
}
