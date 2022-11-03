package com.oneself.datasource.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: Mybatis 配置
 * @Title: MybatisConfig
 * @Author wen
 * @Date: 2022/9/4 16:06
 */
@Configuration
public class MybatisConfig {

    /**
     * Mybatis 分页拦截器
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
       return new PaginationInterceptor();
    }
}
