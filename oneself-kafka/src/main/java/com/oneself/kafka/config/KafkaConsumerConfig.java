package com.oneself.kafka.config;

import com.oneself.kafka.Listener.KafkaConsumerListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import com.oneself.common.utils.PropertiesUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Title: KafkaConsumerConfig
 * @Author wen
 * @Date: 2022/7/30 15:14
 */
@Slf4j
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    public KafkaConsumerConfig() {
        log.info("kafka消费者配置加载...");
    }

    @Bean
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory(consumerProperties());
    }

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory =
                new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public Map<String, Object> consumerProperties() {
        Map<String, Object> props = new HashMap<String, Object>();
        PropertiesUtil propertiesUtil = new PropertiesUtil("kafka.properties");
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, propertiesUtil.getString("kafka.consumer.bootstrap.servers"));
        props.put(ConsumerConfig.GROUP_ID_CONFIG, propertiesUtil.getString("kafka.consumer.group.id"));
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, propertiesUtil.getString("kafka.consumer.enable.auto.commit"));
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, propertiesUtil.getString("kafka.consumer.auto.commit.interval.ms"));
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, propertiesUtil.getString("kafka.consumer.session.timeout.ms"));
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, propertiesUtil.getString("kafka.consumer.key.deserializer"));
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, propertiesUtil.getString("kafka.consumer.value.deserializer"));
        return props;
    }

    @Bean
    public KafkaConsumerListener kafkaConsumerListener() {
        return new KafkaConsumerListener();
    }

}
