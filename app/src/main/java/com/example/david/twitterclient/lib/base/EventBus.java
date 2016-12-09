package com.example.david.twitterclient.lib.base;

/**
 * Created by David on 16/9/16.
 */
public interface EventBus {
    void register(Object suscriber);
    void unregister(Object suscriber);
    void post(Object event);
}
