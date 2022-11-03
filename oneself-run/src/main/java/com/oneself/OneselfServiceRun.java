package com.oneself;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Title: OneselfServiceRun
 * @Author wen
 * @Date: 2022/7/28 18:23
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class OneselfServiceRun {
    public static void main(String[] args) {
        SpringApplication.run(OneselfServiceRun.class,args);
    }


}
