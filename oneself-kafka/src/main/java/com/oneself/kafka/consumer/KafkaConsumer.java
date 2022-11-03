package com.oneself.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Title: KafkaConsumer
 * @Author wen
 * @Date: 2022/8/26 15:37
 */
@Slf4j
@Component
public class KafkaConsumer {


    @KafkaListener(topics = {"topic"})
    public void onMessage(ConsumerRecord<?, ?> record){
        // 消费的哪个topic、partition的消息,打印出消息内容
        log.info("简单消费：topic{}-partition{}-value{}",record.topic(),record.partition(),record.value());
    }


    /**
     * 分区消费
     */
    @KafkaListener(containerGroup="topic-group",topicPartitions = {@TopicPartition(topic = "topic",partitions = {"0","1"})})
    public void partitionsMessage(){

    }

}
