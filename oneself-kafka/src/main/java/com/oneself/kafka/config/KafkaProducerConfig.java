package com.oneself.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import com.oneself.common.utils.PropertiesUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Title: KafkaProducerConfig
 * @Author wen
 * @Date: 2022/7/30 15:34
 */
@Slf4j
@Configuration
@EnableKafka
public class KafkaProducerConfig {

    public KafkaProducerConfig() {
        log.info("kafka消费者配置加载...");
    }

    @Bean
    public ProducerFactory<Integer, String> producerFactory() {
        return new DefaultKafkaProducerFactory(producerProperties());
    }


    @Bean
    public Map<String, Object> producerProperties() {
        Map<String, Object> props = new HashMap<String, Object>();
        PropertiesUtil propertiesUtil = new PropertiesUtil("kafka.properties");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, propertiesUtil.getString("kafka.producer.bootstrap.servers"));
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, propertiesUtil.getString("kafka.producer.key.serializer"));
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, propertiesUtil.getString("kafka.producer.value.serializer"));
        props.put(ProducerConfig.RETRIES_CONFIG, propertiesUtil.getInteger("kafka.producer.retries"));
        props.put(ProducerConfig.BATCH_SIZE_CONFIG,propertiesUtil.getInteger("kafka.producer.batch.size", 1048576));
        props.put(ProducerConfig.LINGER_MS_CONFIG, propertiesUtil.getInteger("kafka.producer.linger.ms"));
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, propertiesUtil.getLong("kafka.producer.buffer.memory", 33554432L));
        props.put(ProducerConfig.ACKS_CONFIG,propertiesUtil.getString("kafka.producer.acks", "all"));
        return props;
    }


    @Bean
    public KafkaTemplate<Integer, String> kafkaTemplate() {
        PropertiesUtil propertiesUtil = new PropertiesUtil("kafka.properties");
        KafkaTemplate kafkaTemplate = new KafkaTemplate<Integer, String>(producerFactory(), true);
        kafkaTemplate.setDefaultTopic(propertiesUtil.getString("kafka.producer.defaultTopic", "default"));
        return kafkaTemplate;
    }
}
