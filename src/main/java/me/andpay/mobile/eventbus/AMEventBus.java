package me.andpay.mobile.eventbus;

/**
 * 对第三方EventBus接口
 */
public interface AMEventBus {

    /**
     * 注册事件
     * @param subscriber
     */
    public void register(Object subscriber);

    /**
     * 注册事件
     * @param subscriber
     * @param priority
     */
    public void register(Object subscriber, int priority);

    /**
     * 注册sticky事件
     * @param subscriber
     */
    public void registerSticky(Object subscriber);

    /**
     * 注册sticky事件
     * @param subscriber
     * @param priority
     */
    public void registerSticky(Object subscriber, int priority);


    /**
     * 是否注册了事件
     * @param subscriber
     * @return
     */
    public  boolean isRegistered(Object subscriber);

    /**
     * 注销事件
     * @param subscriber
     */
    public  void unregister(Object subscriber);

    /**
     * 推送事件
     * @param event
     */
    public void post(Object event);


    /**
     * 取消事件
     * @param event
     */
    public void cancelEventDelivery(Object event);

    /**
     * 推送Sticky事件
     * @param event
     */
    public void postSticky(Object event);

    /**
     * 获取Sticky事件
     * @param eventType
     */
    public <T> T getStickyEvent(Class<T> eventType);

    /**
     * 移除Sticky事件
     * @param eventType
     */
    public <T> T removeStickyEvent(Class<T> eventType);

    /**
     * 移除Sticky事件
     * @param event
     */
    public boolean removeStickyEvent(Object event);

    /**
     * 移除所有Sticky事件
     */
    public void removeAllStickyEvents();

    /**
     * 判断监听者是否有事件
     * @param eventClass
     * @return
     */
    public boolean hasSubscriberForEvent(Class<?> eventClass);
}
