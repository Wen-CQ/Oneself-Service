package com.oneself.common.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Title: RequestListener
 * @Author wen
 * @Date: 2022/8/20 0:23
 */
@Slf4j
public class RequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
         log.info("RequestListener 销毁...");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest req = (HttpServletRequest) servletRequestEvent.getServletRequest();
        String requestURI = req.getRequestURI();
        log.info("被调用 requestURI：{},",requestURI);
    }
}
