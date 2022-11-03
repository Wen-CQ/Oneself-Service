package com.oneself.common.config;

import com.oneself.common.filter.UrlFilter;
import com.oneself.common.interceptor.UrlInterceptor;
import com.oneself.common.listener.RequestListener;
import com.oneself.common.listener.UserSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Title: WebConfig
 * @Author wen
 * @Date: 2022/8/19 23:47
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

   @Autowired
   UrlInterceptor urlInterceptor;

    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(urlInterceptor);
    }

    /**
     * 注册过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new UrlFilter());
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    /**
     * 注册监听器
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean registrationBean(){
        ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean();
        registrationBean.setListener(new RequestListener());
        registrationBean.setListener(new UserSessionListener());
        return registrationBean;
    }

}
