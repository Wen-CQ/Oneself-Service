package com.oneself.common.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Title: UserSeesionListener
 * @Author wen
 * @Date: 2022/8/20 0:18
 */
@Slf4j
public class UserSessionListener implements HttpSessionListener {

    public static AtomicInteger userCount = new AtomicInteger(0);

    @Override
    public synchronized  void sessionCreated(HttpSessionEvent httpSessionEvent) {
        userCount.getAndIncrement();
        httpSessionEvent.getSession().getServletContext().setAttribute("sessionCount", userCount.get());
        log.info("【在线人数】人数增加为:{}",userCount.get());
        //此处可以在ServletContext域对象中为访问量计数，然后传入过滤器的销毁方法
        //在销毁方法中调用数据库入库，因为过滤器生命周期与容器一致

    }

    @Override
    public synchronized  void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        userCount.getAndDecrement();
        httpSessionEvent.getSession().getServletContext().setAttribute("sessionCount", userCount.get());
        log.info("【在线人数】人数减少为:{}",userCount.get());
    }
}
