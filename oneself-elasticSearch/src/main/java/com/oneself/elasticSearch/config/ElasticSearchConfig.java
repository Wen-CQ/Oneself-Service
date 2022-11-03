package com.oneself.elasticSearch.config;

import com.oneself.common.utils.PropertiesUtil;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Title: ElasticSearchConfig
 * @Author wen
 * @Date: 2022/7/31 4:35
 */
@Configuration
public class ElasticSearchConfig {


    @Bean
    public RestHighLevelClient restHighLevelClient(){
        PropertiesUtil propertiesUtil = new PropertiesUtil("es.properties");
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(
                propertiesUtil.getString("elasticsearch.rest.client.url"),
                propertiesUtil.getInteger("elasticsearch.rest.client.port"),
                "http"))
        );
        return client;
    }

}
