package com.yale.eventbus;

import de.greenrobot.event.EventBus;

/**
 * GreenRobotEventBus 的实现
 */
public class AMGreenRobotEventBus implements  AMEventBus{

    public  EventBus eventBus;

    public AMGreenRobotEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
    public AMGreenRobotEventBus() {
        this.eventBus = EventBus.getDefault();
    }

    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    public void register(Object subscriber, int priority) {
        eventBus.register(subscriber,priority);
    }

    public void registerSticky(Object subscriber) {
        eventBus.registerSticky(subscriber);
    }

    public void registerSticky(Object subscriber, int priority) {
        eventBus.registerSticky(subscriber,priority);
    }

    public boolean isRegistered(Object subscriber) {
        return eventBus.isRegistered(subscriber);
    }

    public void unregister(Object subscriber) {
        eventBus.unregister(subscriber);
    }

    public void post(Object event) {
        eventBus.post(event);
    }

    public void cancelEventDelivery(Object event) {
        eventBus.cancelEventDelivery(event);
    }

    public void postSticky(Object event) {
        eventBus.postSticky(event);
    }

    public <T> T getStickyEvent(Class<T> eventType) {
        return eventBus.getStickyEvent(eventType);
    }

    public <T> T removeStickyEvent(Class<T> eventType) {
        return eventBus.removeStickyEvent(eventType);
    }

    public boolean removeStickyEvent(Object event) {
        return eventBus.removeStickyEvent(event);
    }

    public void removeAllStickyEvents() {
        eventBus.removeAllStickyEvents();
    }

    @Override
    public boolean hasSubscriberForEvent(Class<?> eventClass) {
        return eventBus.hasSubscriberForEvent(eventClass);
    }
}
