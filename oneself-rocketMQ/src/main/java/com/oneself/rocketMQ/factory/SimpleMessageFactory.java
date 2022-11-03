package com.oneself.rocketMQ.factory;

/**
 * @Description:
 * @Title: SimpleMessageFactory
 * @Author wen
 * @Date: 2022/8/26 16:30
 */
public interface SimpleMessageFactory {

    /**
     * 发送消息
     * @param message
     */
    void sendMessage(String message);

    /**
     * 发送同步消息
     * @param id
     * @param message
     */
    void sendSyncMessage(String id, String message);

    /**
     * 发送异步消息
     * @param id
     * @param message
     */
    void sendAsyncMessage(String id, String message);

    /**
     * 发送单向消息
     * @param id
     * @param message
     */
    void sendOnewayMessage(String id, String message);
}
