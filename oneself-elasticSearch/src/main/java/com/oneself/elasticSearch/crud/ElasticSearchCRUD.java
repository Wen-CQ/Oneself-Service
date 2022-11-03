package com.oneself.elasticSearch.crud;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Title: ElasticSearchCRUD
 * @Author wen
 * @Date: 2022/9/10 18:35
 */
@Component
public class ElasticSearchCRUD {

    @Autowired
    RestHighLevelClient restHighLevelClient;



    public void search(){

    }

}
