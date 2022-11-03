package com.oneself.common.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * @Description:
 * @Title: UrlFilter
 * @Author wen
 * @Date: 2022/8/20 0:00
 */
@Slf4j
public class UrlFilter implements Filter {


    /**
     * 在容器中创建当前过滤器的时候自动调用
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化地址过滤器...");
    }


    /**
     * 在容器中销毁当前过滤器的时候自动调用
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        request.getSession();
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        String requestUri = request.getRequestURI();
        log.info("请求地址是：{}",requestUri);
//        if (requestUri.contains("/learnCourse")||requestUri.contains("/learnQuestion")) {
            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            wrapper.sendRedirect("/login");
//        }

    }

    /**
     * 在容器中销毁当前过滤器的时候自动调用
     */
    @Override
    public void destroy() {
        log.info("销毁过滤器...");
    }
}
